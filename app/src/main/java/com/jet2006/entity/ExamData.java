package com.jet2006.entity;

public class ExamData {

	private String ExamName;
	private String ExamTimer;
	private String ExamLenght;
	private String ExamStatus;

	public static String[] filed() {
		return new String[] { "ExamName", "ExamTimer", "ExamLenght",
				"ExamStatus" };
	}

	public String getExamName() {
		return ExamName;
	}

	public void setExamName(String examName) {
		ExamName = examName;
	}

	public String getExamTimer() {
		return ExamTimer;
	}

	public void setExamTimer(String examTimer) {
		ExamTimer = examTimer;
	}

	public String getExamLenght() {
		return ExamLenght;
	}

	public void setExamLenght(String examLenght) {
		ExamLenght = examLenght;
	}

	public String getExamStatus() {
		return ExamStatus;
	}

	public void setExamStatus(String examStatus) {
		ExamStatus = examStatus;
	}

}
