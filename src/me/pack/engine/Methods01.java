package me.pack.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Methods01 {

	private static String separator = File.separator;

	public static String getFileName(File file) {
		String returnValue = file.getAbsolutePath();
		returnValue = returnValue.substring(returnValue.lastIndexOf(separator) + 1);
		return returnValue;
	}

	public static String getName(String location) {
		String returnValue;
		returnValue = location.substring(location.lastIndexOf(separator) + 1);
		return returnValue;
	}

	public static void printFiles(Model arg, String txt) {
		Map<String, File> map = arg.getMap();
		int index = 1;
		for (Entry<String, File> entry : map.entrySet()) {
			String fileName = getFileName(entry.getValue());
			System.out.println(txt + " : " + index + " " + fileName);
			index++;
		}
	}

	public static boolean isInList(ArrayList<String> list, String arg) {
		for (String extension : list) {
			if (arg.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		return false;
	}
}