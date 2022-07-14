package me.pack.engine.scanners;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import me.pack.engine.Methods01;
import me.pack.engine.Model;

public class AScan extends Model {
	private static ArrayList<String> extensions;
	private static String directory = Model.getDirectory();
	private static AScan scanner = new AScan();
	private static HashMap<String, File> map = new HashMap<String, File>();
	private static String text = "Audio";

	private AScan() {

	}

	public static AScan getAudioScanner() {
		return scanner;
	}

	@Override
	public HashMap<String, File> getMap() {
		return map;
	}

	public String getText() {
		return text;
	}

	private static void makeList() {
		extensions = new ArrayList<String>();
		extensions.add("mp3");
		extensions.add("wav");
		extensions.add("aac");
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
			} else if (isAudio(file)) {
				map.put(file.getAbsolutePath(), file);
			}
		}
	}

	private static boolean isAudio(File file) {
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