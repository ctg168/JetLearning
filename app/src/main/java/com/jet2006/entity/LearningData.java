package com.jet2006.entity;

public class LearningData {

	private int Id;
	private String LearningName;
	private String LearningInfo;
	private String LearningPro;
	private int LearningPic;

	public static String[] field() {
		return new String[] { "Id", "LearningName", "LearningInfo",
				"LearningPro", "LearningPic" };
	}
	
	public LearningData(){}
	
	public LearningData(int id, String learningName, String learningInfo, String learningPro, int learningPic){
		this.setId(id);
		this.setLearningName(learningName);
		this.setLearningInfo(learningInfo);
		this.setLearningPro(learningPro);
		this.setLearningPic(learningPic);
	}
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLearningName() {
		return LearningName;
	}

	public void setLearningName(String learningName) {
		LearningName = learningName;
	}

	public String getLearningInfo() {
		return LearningInfo;
	}

	public void setLearningInfo(String learningInfo) {
		LearningInfo = learningInfo;
	}

	public String getLearningPro() {
		return LearningPro;
	}

	public void setLearningPro(String learningPro) {
		LearningPro = learningPro;
	}

	public int getLearningPic() {
		return LearningPic;
	}

	public void setLearningPic(int learningPic) {
		LearningPic = learningPic;
	}



}
