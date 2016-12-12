package com.google.zxing.client.android.model;

import java.io.Serializable;

public class Lesson implements Serializable {
	private int id;
	private String name;
	private String imgUrl;
	private Boolean state;

	public Lesson() {
		super();
	}
	public Lesson(int id, String name, Boolean state) {
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
}
