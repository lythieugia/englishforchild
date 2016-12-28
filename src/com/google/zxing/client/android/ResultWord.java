package com.google.zxing.client.android;

public class ResultWord {
	String vocabularyId;
	int numberOfWrongTimes;

	public ResultWord(String vocabularyId, int numberOfWrongTimes) {
		super();
		this.vocabularyId = vocabularyId;
		this.numberOfWrongTimes = numberOfWrongTimes;
	}

	public ResultWord() {
		super();
	}

	public String getVocabularyId() {
		return vocabularyId;
	}

	public void setVocabularyId(String vocabularyId) {
		this.vocabularyId = vocabularyId;
	}

	public int getNumberOfWrongTimes() {
		return numberOfWrongTimes;
	}

	public void setNumberOfWrongTimes(int numberOfWrongTimes) {
		this.numberOfWrongTimes = numberOfWrongTimes;
	}
}
