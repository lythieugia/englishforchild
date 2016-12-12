package com.google.zxing.client.android.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.zxing.client.android.Word;

public class Group implements Serializable {
	private int id;
	private String name;
	private String imgUrl;
	private Boolean state;
	private ArrayList<Word> mWordList;

	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Group(int id, String name, Boolean state) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public ArrayList<Word> getWordList() {
		return mWordList;
	}
	public void setWordList(ArrayList<Word> mWordList) {
		this.mWordList = mWordList;
	}
	
}
