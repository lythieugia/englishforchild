package com.google.zxing.client.android;

public class ResultWord {
	String imageId;
	int numberOfWrongTimes;

	public ResultWord(String imageId, int numberOfWrongTimes) {
		super();
		this.imageId = imageId;
		this.numberOfWrongTimes = numberOfWrongTimes;
	}

	public ResultWord() {
		super();
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getNumberOfWrongTimes() {
		return numberOfWrongTimes;
	}

	public void setNumberOfWrongTimes(int numberOfWrongTimes) {
		this.numberOfWrongTimes = numberOfWrongTimes;
	}
}
