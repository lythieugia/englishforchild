package com.google.zxing.client.android;

public class Utils {

	public static String GROUP_CONTENT = "GROUP_CONTENT";
	public static String LESSON_CONTENT = "LESSON_CONTENT";
	public static String LESSON_ID = "LESSON_ID";
    public static boolean textIsNotEmpty(String text) {
        if (text != null && !text.isEmpty() && text.trim().length() > 0 && !text.equals("null") && !text.equals("NULL")) {
            return true;
        }
        return false;
    }
	
}
