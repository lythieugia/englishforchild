package com.google.zxing.client.android;

import java.util.ArrayList;

public class PostGroup {
	private int studentId;
	private ArrayList<ResultWord> results;
	
	public PostGroup() {
		super();
	}
	public PostGroup(int studentId, ArrayList<ResultWord> results) {
		super();
		this.studentId = studentId;
		this.results = results;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public ArrayList<ResultWord> getResults() {
		return results;
	}
	public void setResults(ArrayList<ResultWord> results) {
		this.results = results;
	}
	
	
}
