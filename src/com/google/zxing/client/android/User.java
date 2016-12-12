package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.RequiresPermission.Read;

public class User {
	private int id;
	private String name;
	public static String SHARE_USER_KEY = "USER";

	private static User mInstance;

	private User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static User initInstance(Activity context, int id, String name) {
		if (mInstance == null) {
			saveUser2Local(context, id, name);
			mInstance = new User(id, name);
		}
		return mInstance;
	}
	
	public static User getInstance() {
		return mInstance;
	}
	
	public static User getInstance(Activity context) {
		if (mInstance == null) {
			if (context != null) {
				SharedPreferences sharedPref = context.getSharedPreferences(SHARE_USER_KEY,Context.MODE_PRIVATE);
				int id = sharedPref.getInt("id", 0);
				String name = sharedPref.getString("name", null);
				if(id>0 && textIsNotEmpty(name)){
					mInstance = new User(id, name);
				}
			}
		}
		return mInstance;
	}
	
	 public static boolean textIsNotEmpty(String text) {
	        if (text != null && !text.isEmpty() && text.trim().length() > 0 && !text.equals("null") && !text.equals("NULL")) {
	            return true;
	        }
	        return false;
	    }

	private User(int id, String name, String lesson) {
		super();
		this.id = id;
		this.name = name;
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
	
	public static void saveUser2Local(Activity context,int id, String name){
		SharedPreferences sharedPref = context.getSharedPreferences(SHARE_USER_KEY,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("id", id);
		editor.putString("name", name);
		editor.commit();
	}
	
	public static void removeUserLocal(Activity context){
		SharedPreferences sharedPref = context.getSharedPreferences(SHARE_USER_KEY,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.clear();
		editor.commit();
	}
}
