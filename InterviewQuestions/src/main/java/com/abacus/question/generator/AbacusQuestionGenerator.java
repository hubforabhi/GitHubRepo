package com.abacus.question.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AbacusQuestionGenerator {

	private static final String HTML_START = "<HTML>";
	private static final String HTML_END = "</HTML";
	private static final String HEAD_START = "<HEAD>";
	private static final String HEAD_END = "</HEAD>";
	private static final String TITLE_START = "<TITLE>";
	private static final String TITLE_END = "</TITLE>";
	private static final String BODY_START = "<BODY>";
	private static final String BODY_END = "</BODY>";
	private static final String TABLE_START = "<TABLE>";
	private static final String TABLE_END = "</TABLE>";

	private static final int ANSWER_COUNT = 4;
	private static final int OPERAND_1_LIMIT = 90;
	private static final int OPERAND_2_LIMIT = 9;

	private static final String[] STYLES = new String[] { "<STYLE>", "table {" + "width : 100%;" + "}",
			"th {" + "text-align : center;" + "border : 1px SOLID BLACK;" + "padding : 10px;" + "}",
			"td {" + "text-align : center;" + "border : 1px SOLID BLACK;" + "padding : 10px;" + "}",
			"hr.rounded {" + "border-top: 8px solid #000000;" + "border-radius: 5px;" + "}", "</STYLE>" };

	public static List<QuestionHolder> generateQuestions(int totalNoOfQuestions) {
		Random random = new Random();
		List<QuestionHolder> questionList = new LinkedList<>();
		for (int i = 0; i < totalNoOfQuestions; i++) {
			int operand1 = random.nextInt(OPERAND_1_LIMIT);
			while(operand1<10) {
				operand1 = random.nextInt(OPERAND_1_LIMIT);
			}
			int operand2 = random.nextInt(OPERAND_2_LIMIT);
			boolean isAddition = random.nextBoolean();
			int result = operand1 - operand2;
			if (isAddition) {
				result = operand1 + operand2;
			}
			// System.out.println((i+1)+". "+operand1+(isAddition? " + " : " - ")+operand2+"
			// = "+result);

			QuestionHolder holder = new QuestionHolder();
			holder.setOperand1(operand1);
			holder.setAddition(isAddition);
			holder.setOperand2(operand2);
			int[] answers = new int[ANSWER_COUNT];
			int correctAnswerIndex = random.nextInt(ANSWER_COUNT - 1);
			for (int j = 0; j < ANSWER_COUNT; j++) {
				if (j == correctAnswerIndex) {
					answers[j] = result;
				} else {
					answers[j] = random.nextInt(OPERAND_1_LIMIT);
				}
			}
			holder.setAnswer(answers);
			questionList.add(holder);
		}
		return questionList;
	}

	public static StringBuffer generateHTMLFragment(List<QuestionHolder> questionList) {
		StringBuffer contentBuffer = new StringBuffer();
		contentBuffer.append(HTML_START);
		contentBuffer.append(HEAD_START);
		contentBuffer.append(TITLE_START);
		contentBuffer.append("ABACUS QUESTION " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
		contentBuffer.append(TITLE_END);
		contentBuffer.append(HEAD_END);
		for (int i = 0; i < STYLES.length; i++) {
			contentBuffer.append(STYLES[i]);
		}
		contentBuffer.append(BODY_START);

		for (int i = 0; i < questionList.size();) {
			contentBuffer.append(TABLE_START);
			contentBuffer.append("<tr>");
			int step = 0;
			for (int j = i; j < questionList.size(); j++) {
				contentBuffer.append("<th>");
				contentBuffer.append("Question " + (j + 1));
				contentBuffer.append("</th>");
				step++;
				if (step == 10)
					break;
			}
			contentBuffer.append("</tr>");
			contentBuffer.append("<tr>");
			step = 0;
			for (int j = i; j < questionList.size(); j++) {
				contentBuffer.append("<td>");
				contentBuffer.append(questionList.get(j).getOperand1() + "<br/>");
				contentBuffer.append((questionList.get(j).isAddition() ? "+" : "-") + questionList.get(j).getOperand2());
				contentBuffer.append("</td>");
				step++;
				if (step == 10)
					break;
			}
			contentBuffer.append("</tr>");
			contentBuffer.append("<tr>");
			step = 0;
			for (int j = i; j < questionList.size(); j++) {
				contentBuffer.append("<td>");
				for (int k = 0; k < questionList.get(j).getAnswer().length; k++) {
					contentBuffer.append("<input type=radio name=question" + (j) + " value="+ questionList.get(j).getAnswer()[k] + ">" + questionList.get(j).getAnswer()[k] + "<br/>");
				}
				contentBuffer.append("</td>");
				step++;
				if (step == 10)
					break;
			}
			contentBuffer.append("</tr>");
			contentBuffer.append(TABLE_END);
			contentBuffer.append("<hr class=\"rounded\"/>");
			i = i + step;
			if (i >= questionList.size())
				break;
		}
		contentBuffer.append(BODY_END);
		contentBuffer.append(HTML_END);

		return contentBuffer;
	}

	private static void showAndSaveFile(JFrame frame, String htmlSource) {
		JFileChooser fileSaver = new JFileChooser(System.getenv("user.home"));
		fileSaver.setDialogType(JFileChooser.SAVE_DIALOG);
		fileSaver.setDialogTitle("Specify a File Name to Save Question Paper");
		fileSaver.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fileSaver.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileSaver.getSelectedFile();
			String filePath = fileToSave.getAbsolutePath();
			if (!filePath.endsWith(".html")) {
				filePath = filePath + ".html";
			}
			Path path = Paths.get(filePath);
			try {
				if (Files.notExists(path))
					Files.createFile(path);
				Files.writeString(path, htmlSource, StandardOpenOption.WRITE);
				JOptionPane.showMessageDialog(frame, "File Created Successfully.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static int getNoOfQuestions(JFrame frame) {
		int attempt = 0;
		int noOfQuestion = -1;
		while(attempt<3) {
			String noOfQuestions = JOptionPane.showInputDialog(frame, "Please Input Total Number Of Questions","No Of Questions", JOptionPane.QUESTION_MESSAGE);
			try {
				noOfQuestion = Integer.parseInt(noOfQuestions);
				if(noOfQuestion <0) {
					JOptionPane.showMessageDialog(frame, "Negative Number is not acceptable, Please try again", "No Of Questions", JOptionPane.INFORMATION_MESSAGE);					
				} else if(noOfQuestion == 0) {
					JOptionPane.showMessageDialog(frame, "Zero Value is not acceptable, Please try again", "No Of Questions", JOptionPane.INFORMATION_MESSAGE);					
				} else {
					break;
				}
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame, "Invalid Character Entered, Please try again", "No Of Questions", JOptionPane.INFORMATION_MESSAGE);
			}
			attempt++;
		}
		return noOfQuestion;
	}

	public static void main(String... args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame rootFrame = new JFrame();
		int noOfQuestions = getNoOfQuestions(rootFrame);
		if(noOfQuestions >0) {
			List<QuestionHolder> questionList = generateQuestions(noOfQuestions);
			StringBuffer contentBuffer = generateHTMLFragment(questionList);
			showAndSaveFile(rootFrame, contentBuffer.toString());
		}
	}
}