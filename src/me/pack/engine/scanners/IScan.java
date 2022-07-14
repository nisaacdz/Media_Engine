package me.pack.engine.scanners;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import me.pack.engine.Methods01;
import me.pack.engine.Model;

public class IScan extends Model {

	private static ArrayList<String> extensions;
	private static String directory = Model.getDirectory();
	private static IScan scanner = new IScan();
	private static HashMap<String, File> map = new HashMap<String, File>();
	private static String text = "Image";

	private IScan() {
	}

	public static IScan getImageScanner() {
		return scanner;
	}

	@Override
	public HashMap<String, File> getMap() {
		return map;
	}

	public String getText() {
		return text;
	}

	private void scan() {
		makeList();
		File folder = new File(directory);
		File[] files = folder.listFiles();
		iterate(files);
	}

	private void iterate(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				iterate(file.listFiles());
			} else if (isImage(file)) {
				map.put(file.getAbsolutePath(), file);
			}
		}
	}

	private static void makeList() {
		extensions = new ArrayList<String>();
		extensions.add("png");
		extensions.add("jpg");
		extensions.add("gif");
	}

	private static boolean isImage(File file) {
		String name = file.getAbsolutePath();
		try {
			String extension = name.substring(name.lastIndexOf(".") + 1);
			if (Methods01.isInList(extensions, extension)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	protected Void doInBackground() throws Exception {
		scan();
		return null;
	}
}