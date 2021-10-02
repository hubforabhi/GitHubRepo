package com.abacus.question.generator;

public class QuestionHolder {
	private int operand1;
	private int operand2;
	private boolean addition;
	
	private int answer[];

	public int getOperand1() {
		return operand1;
	}

	public void setOperand1(int operand1) {
		this.operand1 = operand1;
	}

	public int getOperand2() {
		return operand2;
	}

	public void setOperand2(int operand2) {
		this.operand2 = operand2;
	}

	public boolean isAddition() {
		return addition;
	}

	public void setAddition(boolean addition) {
		this.addition = addition;
	}

	public int[] getAnswer() {
		return answer;
	}

	public void setAnswer(int[] answer) {
		this.answer = answer;
	}
}
