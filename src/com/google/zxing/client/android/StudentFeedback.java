package com.google.zxing.client.android;

public class StudentFeedback {
	private int studentId;
	private int lessonId;
	private int feeling;
	
	public StudentFeedback() {
		super();
	}
	public StudentFeedback(int studentId, int lessonId, int feeling) {
		super();
		this.studentId = studentId;
		this.lessonId = lessonId;
		this.feeling = feeling;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public int getFeeling() {
		return feeling;
	}
	public void setFeeling(int feeling) {
		this.feeling = feeling;
	}
	
	
}
