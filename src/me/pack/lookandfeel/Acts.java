package me.pack.lookandfeel;

import java.awt.Color;

public class Acts {
	
	public static Color getRandomColor() {
		int r = (int) (255 * Math.random());
		int g = (int) (255 * Math.random());
		int b = (int) (255 * Math.random());
		return new Color(r, g, b);
	}

	public static String formName(String fileName) {
		String returnValue;
		String temp = fileName;
		if (temp.contains("\\")) {
			returnValue = temp.substring(temp.lastIndexOf("\\") + 1);
		} else {
			returnValue = temp;
		}
		return returnValue;
	}

	public static String getProjectVersionName() {
		return "MEDIA_ENGINE 1.0.0";
	}
}
