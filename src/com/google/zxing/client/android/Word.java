package com.google.zxing.client.android;

import java.io.Serializable;

public class Word implements Serializable  {
	private int id;
	private String word;
	private String imageId;
	private String imageFile;
	private Boolean mPassed =  false;
	private int mWrongCount = 0;
	
	public Word() {
		super();
	}
	
	public Word(int id,String word, String imageId, String imageFile) {
		super();
		this.id = id;
		this.word = word;
		this.imageId = imageId;
		this.imageFile = imageFile;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public Boolean getPassed() {
		return mPassed;
	}

	public void setPassed(Boolean mPassed) {
		this.mPassed = mPassed;
	}

	public int getWrongCount() {
		return mWrongCount;
	}

	public void setWrongCount(int mWrongCount) {
		this.mWrongCount = mWrongCount;
	}
}
