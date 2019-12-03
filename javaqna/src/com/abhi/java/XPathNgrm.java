package com.abhi.java;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathNgrm {
	private static final String EPIC_NAME = "Steel Vessel Rules";
	private static final String COMMA = ",";
	private static final String DOT = ".";
	private static final String COMMON_ROOT_FOLDER = "D:\\XSD\\automate\\xml\\";
	
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private FileWriter fw;
	final private XPath xPath = XPathFactory.newInstance().newXPath();
	
	public XPathNgrm() throws ParserConfigurationException {
		dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
		fw = null;
	}
	
	public XPathNgrm(String outputFile) throws ParserConfigurationException {
		this();
		try {
			fw = new FileWriter(outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		if(fw != null) {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	Consumer<String> loggingConsumer = s -> {
		if(fw != null) {
			try {
				fw.write(s);
				fw.write(System.lineSeparator());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(s);
		}
	};
	
	Function<Document, String> topicTitleConsumer = doc -> {
		String expression = "/concept/title";
		Node rootTitleNode = null;
		try {
			rootTitleNode = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return rootTitleNode.getTextContent().contains("\n") ? rootTitleNode.getTextContent().replaceAll("\n","") : rootTitleNode.getTextContent();
	};

	BiFunction<String[], Element, List<String>> secondLevelSectionDivVisitor = (prefix, secondLevelSectionDivElement) -> {		
		NodeList secondLevelSectionDivNodeList = secondLevelSectionDivElement.getElementsByTagName("sectiondiv");
		List<String> secondLevelSectionDivList = new ArrayList<>();
		for(int i=0;i<secondLevelSectionDivNodeList.getLength();i++) {
			Element secondLevelSectionDiv = (Element) secondLevelSectionDivNodeList.item(i);
			if(secondLevelSectionDiv.getElementsByTagName("ptitle").getLength()>0) {
				String secondLevelSectionDivName = secondLevelSectionDiv.getElementsByTagName("ptitle").item(0).getTextContent().trim();
				secondLevelSectionDivList.add(secondLevelSectionDivName);
				loggingConsumer.accept(prefix[0]+COMMA+prefix[1]+DOT+i+COMMA+secondLevelSectionDivName);
			}			
		}	
		return secondLevelSectionDivList;
	};

	BiConsumer<String[], Element> sectionDivVisitor = (prefix, sectionDivElement) -> {		
		NodeList sectionDivNodeList =  sectionDivElement.getElementsByTagName("sectiondiv");
		List<String> secondLevelSectionDivList = new ArrayList<>();
		for(int i=0;i<sectionDivNodeList.getLength();i++) {
			Element sectionDiv = (Element) sectionDivNodeList.item(i);
			String sectionDivNumberLabel = sectionDiv.getAttribute("numberLabel");
			if(sectionDiv.getElementsByTagName("ptitle").item(0) != null) {
				String sectionDivTitle = sectionDiv.getElementsByTagName("ptitle").item(0).getTextContent().trim();
				if(!secondLevelSectionDivList.contains(sectionDivTitle)) {
					loggingConsumer.accept(prefix[0]+COMMA+prefix[1]+DOT+sectionDivNumberLabel+COMMA+sectionDivTitle);
					if(sectionDiv.getElementsByTagName("sectiondiv").getLength()>0) {
						secondLevelSectionDivList = secondLevelSectionDivVisitor.apply(new String[] {prefix[0]+COMMA+prefix[1]+DOT+sectionDivNumberLabel+COMMA+sectionDivTitle,prefix[1]+DOT+sectionDivNumberLabel}, sectionDiv);
					}				
				}				
			}
		}
	};		
	
	BiConsumer<String[], Document> sectionVisitor = (prefix, doc) -> {
		String sectionDivExpression = "/concept/conbody/section";
		String title = topicTitleConsumer.apply(doc).trim();		
		loggingConsumer.accept(prefix[0]+COMMA+prefix[1]+COMMA+title);
		try {
			NodeList sectionNodeList = (NodeList) xPath.compile(sectionDivExpression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < sectionNodeList.getLength(); i++) {
				Node nNode = sectionNodeList.item(i);	
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element sectionElement = (Element) nNode;
					String sectionNumberLabel = sectionElement.getAttribute("numberLabel");
					NodeList sectionTitleNodeList = sectionElement.getElementsByTagName("title");
					String sectionTitle = ((Element)sectionTitleNodeList.item(0)).getTextContent().trim();
					if(sectionTitle.contains("\n")) sectionTitle = sectionTitle.replaceAll("\n","");
					loggingConsumer.accept(prefix[0]+COMMA+prefix[1]+COMMA+title+COMMA+sectionNumberLabel+COMMA+sectionTitle);
					if(sectionElement.getElementsByTagName("sectiondiv").getLength()>0) {
						sectionDivVisitor.accept(new String[] {prefix[0]+COMMA+prefix[1]+COMMA+title+COMMA+sectionNumberLabel+COMMA+sectionTitle,sectionNumberLabel}, sectionElement);	
					}						
				}
			}	
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	};	
	
	Function<String, String> sectionAppedixAnnex = startsWithSection -> {
		String typeOfSection = "Section";
		if(startsWithSection.startsWith("ANNEX") || startsWithSection.startsWith("Annex"))   typeOfSection = "Annex";
		if(startsWithSection.startsWith("APPENDIX") || startsWithSection.startsWith("Appendix"))   typeOfSection = "Appendix";
		if(startsWithSection.startsWith("FOREWARD") || startsWithSection.startsWith("Foreward"))   typeOfSection = "Foreward";
		return typeOfSection;
	};
	
	Consumer<Path> fileWalkerConsumer = sPath-> {
		Path destPath = Paths.get(COMMON_ROOT_FOLDER, sPath.toFile().getName());			
		try {
			Files.copy(sPath, destPath, StandardCopyOption.REPLACE_EXISTING);
			//System.out.println(sPath.getName(4)+"::"+sPath.getName(5)+"::"+sPath.getName(6));
			String partInFileName = sPath.getName(4).toString();
			int firstIndex = partInFileName.indexOf(" ");
			String partValue = "";
			if(firstIndex>=0) {
				int secondIndex = partInFileName.indexOf(" ", firstIndex+1);
				if(secondIndex != -1)
				partValue = partInFileName.substring(firstIndex+1, secondIndex);
			}				
			
			String chapterInFileName = sPath.getName(5).toString();
			firstIndex = chapterInFileName.indexOf(" ");
			String chapterValue="";
			if(firstIndex>=0) {
				int secondIndex = chapterInFileName.indexOf(" ", firstIndex+1);
				if(secondIndex != -1) {
					chapterValue = chapterInFileName.substring(firstIndex+1, secondIndex);
				} else {
					chapterValue = chapterInFileName.substring(firstIndex+1, chapterInFileName.length()-1);
				}
			} else {
				chapterValue = chapterInFileName;
			}
			
			String typeOfSection ="Section";
			String sectionInFileName = sPath.getName(6).toString();
			firstIndex = sectionInFileName.indexOf(" ");
			String sectionValue="";
			if(firstIndex>=0) {
				int secondIndex = sectionInFileName.indexOf(" ", firstIndex+1);
				String startsWithSection = sectionInFileName.substring(0, firstIndex);
				typeOfSection = sectionAppedixAnnex.apply(startsWithSection);
				if(startsWithSection.startsWith("SECTION") || startsWithSection.startsWith("Section") || 
						startsWithSection.startsWith("ANNEX") || startsWithSection.startsWith("Annex") ||
							startsWithSection.startsWith("APPENDIX") || startsWithSection.startsWith("Appendix") ||
								startsWithSection.startsWith("FOREWARD") || startsWithSection.startsWith("Foreward")) {
					if(secondIndex != -1) {
						sectionValue = sectionInFileName.substring(firstIndex+1, secondIndex);
					} else {
						//sectionValue = sectionInFileName.substring(firstIndex+1, sectionInFileName.length()-1);
						int indexOfDotInFileName = sectionInFileName.indexOf(".");
						if(indexOfDotInFileName>0)
						sectionValue = sectionInFileName.substring(firstIndex+1, indexOfDotInFileName);
					}						
				} else {
					int indexOfDotInFileName = sectionInFileName.indexOf(".");
					if(indexOfDotInFileName>0)
					sectionValue = sectionInFileName.substring(0, indexOfDotInFileName);						
				}
			} else {
				int indexOfDotInFileName = sectionInFileName.indexOf(".");
				if(indexOfDotInFileName>0)
				sectionValue = sectionInFileName.substring(0, indexOfDotInFileName);
			}
			Document doc = dBuilder.parse(destPath.toFile());
			doc.getDocumentElement().normalize();			
			sectionVisitor.accept(new String[] {EPIC_NAME+COMMA+"Part"+COMMA+partValue+COMMA+"Chapter"+COMMA+chapterValue+COMMA+typeOfSection,sectionValue}, doc);			
			Files.delete(destPath);
		} catch (IOException | SAXException e) {
			e.printStackTrace();
		}
	};
	
	public static void runUnitTestCase() throws ParserConfigurationException, IOException {
		Path path = Paths.get("D:\\XSD\\automate\\xml\\NGRM Test Chapters");		
		//XPathNgrm ngrmTopicFileVisitor = new XPathNgrm("D:\\XSD\\automate\\Publication.csv");
		XPathNgrm ngrmTopicFileVisitor = new XPathNgrm();
		Files.walk(path).filter(Files::isRegularFile).forEach(ngrmTopicFileVisitor.fileWalkerConsumer);
		ngrmTopicFileVisitor.close();
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException {
		long startMilliSeconds = System.currentTimeMillis();
		//Path path = Paths.get("D:\\XSD\\automate\\xml\\NGRM CHapters");		
		//XPathNgrm ngrmTopicFileVisitor = new XPathNgrm("D:\\XSD\\automate\\Publication.csv");		
		//Files.walk(path).filter(Files::isRegularFile).forEach(ngrmTopicFileVisitor.fileWalkerConsumer);
		//ngrmTopicFileVisitor.close();
		
		runUnitTestCase();		
		
		System.out.println("Total Time "+(System.currentTimeMillis() - startMilliSeconds));		
	}
}