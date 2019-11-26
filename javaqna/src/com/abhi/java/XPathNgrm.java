package com.abhi.java;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.function.BiConsumer;
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

	public static void main(String[] args) throws IOException, ParserConfigurationException {
		
		String rootFolder = "D:\\XSD\\automate\\xml\\NGRM CHapters";
		Path path = Paths.get(rootFolder);		
		long startMilliSeconds = System.currentTimeMillis();
		FileWriter fw = new FileWriter("D:\\XSD\\automate\\Publication.csv");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		final XPath xPath = XPathFactory.newInstance().newXPath();
		
		Function<Document, String> topicTitleConsumer = doc -> {
			String expression = "/concept/title";
			Node rootTitleNode = null;
			try {
				rootTitleNode = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			return rootTitleNode.getTextContent();
		};
		
		BiConsumer<String, Element> secondLevelSectionDivVisitor = (prefix, sectionElement) -> {		
			NodeList secondLevelSectionDivNodeList = sectionElement.getElementsByTagName("sectiondiv");
			for(int i=0;i<secondLevelSectionDivNodeList.getLength();i++) {
				Element secondLevelSectionDiv = (Element) secondLevelSectionDivNodeList.item(i);
				if(secondLevelSectionDiv.getElementsByTagName("ptitle").getLength()>0)
					System.out.println(prefix+"/"+secondLevelSectionDiv.getElementsByTagName("ptitle").item(0).getTextContent());
			}				
		};
		
		BiConsumer<String, Element> sectionDivVisitor = (prefix, sectionElement) -> {
			String sectionDivExpression = "/concept/conbody/section/sectiondiv";
			try {
				NodeList sectionDivNodeList = (NodeList) xPath.compile(sectionDivExpression).evaluate(sectionElement, XPathConstants.NODESET);
				for(int i=0;i<sectionDivNodeList.getLength();i++) {
					Element sectionDivElement = (Element) sectionDivNodeList.item(i);
					//System.out.println("Section Division Label :: "+sectionDivElement.getAttribute("numberLabel"));					
					String sectionDivTitle = sectionDivElement.getElementsByTagName("ptitle").item(0).getTextContent().trim();
					if(sectionElement.getElementsByTagName("sectiondiv").getLength()>0) {
						secondLevelSectionDivVisitor.accept(prefix+"/"+sectionDivTitle, sectionDivElement);	
					} else {
						System.out.println(prefix+"/"+sectionDivTitle);
					}
				}
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
		};		
		
		BiConsumer<String, Document> sectionVisitor = (prefix, doc) -> {
			String sectionDivExpression = "/concept/conbody/section";
			String title = topicTitleConsumer.apply(doc).trim();
			System.out.println("/"+title);
			try {
				NodeList sectionNodeList = (NodeList) xPath.compile(sectionDivExpression).evaluate(doc, XPathConstants.NODESET);
				for (int i = 0; i < sectionNodeList.getLength(); i++) {
					Node nNode = sectionNodeList.item(i);	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element sectionElement = (Element) nNode;
						//System.out.print("Section Number Label : " + sectionElement.getAttribute("numberLabel"));
						NodeList sectionTitleNodeList = sectionElement.getElementsByTagName("title");
						String sectionTitle = ((Element)sectionTitleNodeList.item(0)).getTextContent();						
						if(sectionElement.getElementsByTagName("sectiondiv").getLength()>0) {
							sectionDivVisitor.accept(title+"/"+sectionTitle, sectionElement);	
						}						
					}
				}	
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
		};	
		
		Consumer<Path> fileWalkerConsumer = sPath-> {
			Path destPath = Paths.get("D:\\XSD\\automate\\xml\\", sPath.toFile().getName());			
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
					if(startsWithSection.startsWith("ANNEX") || startsWithSection.startsWith("Annex"))   typeOfSection = "Annex";
					if(startsWithSection.startsWith("APPENDIX") || startsWithSection.startsWith("Appendix"))   typeOfSection = "Appendix";
					if(startsWithSection.startsWith("FOREWARD") || startsWithSection.startsWith("Foreward"))   typeOfSection = "Foreward";
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
				//System.out.println("Steel Vessel Rules,Part,"+partValue+",Chapter,"+chapterValue+",Section,"+sectionValue);
				Document doc = dBuilder.parse(destPath.toFile());
				doc.getDocumentElement().normalize();			
				System.out.print("Steel Vessel Rules,Part,"+partValue+",Chapter,"+chapterValue+","+typeOfSection+","+sectionValue);
				sectionVisitor.accept("Steel Vessel Rules,Part,"+partValue+",Chapter,"+chapterValue+",Section,"+sectionValue+",", doc);
				//fw.write(System.lineSeparator());
				Files.delete(destPath);
			} catch (IOException | SAXException e) {
				e.printStackTrace();
			}
		};
		Files.walk(path).filter(Files::isRegularFile).forEach(fileWalkerConsumer);
		long endMilliSeconds = System.currentTimeMillis();
		fw.close();
		System.out.println("Total Time "+(endMilliSeconds - startMilliSeconds));		
	}
}