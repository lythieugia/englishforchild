package com.google.zxing.client.android;

import java.util.ArrayList;

import android.support.annotation.ArrayRes;

public class WordGroup {
	private int groupId;
	private String groupName;
	private Boolean passed;
	private ArrayList<Word> wordList;

	
	public WordGroup() {
		super();
	}
	public WordGroup(int groupId, String groupName,ArrayList<Word> wordList) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.wordList = wordList;
	}
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public ArrayList<Word> getWordList() {
		return wordList;
	}

	public void setWordList(ArrayList<Word> wordList) {
		this.wordList = wordList;
	}
}
