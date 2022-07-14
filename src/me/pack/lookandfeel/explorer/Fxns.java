package me.pack.lookandfeel.explorer;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class Fxns {

	public static void setAllInvisible(Component[] args) {
		for (Component comp : args) {
			comp.setVisible(false);
		}
	}

	public static void setAllVisible(Component[] args) {
		for (Component comp : args) {
			comp.setVisible(true);
		}
	}

	public static JPane getPFolder(File ff, JPane panel, JPanel container) {
		JPane returnValue = null;
		if (ff.isDirectory()) {
			returnValue = panel;
		} else if (ff.isFile()) {

			File[] n = ff.getParentFile().listFiles();
			File[] included = getIncludedFiles(n);

			int iC = 0;

			for (File file : included) {
				++iC;
				if (file.equals(ff)) {
					break;
				}
			}


			int x = panel.getX() - 10;
			int y = panel.getY() - (40 * iC);

			Component comp = container.getComponentAt(x, y);

			returnValue = (JPane) comp;
		}
		return returnValue;
	}

	public static boolean dirIsEmpty(File folder) {
		Path dir;
		try {
			dir = Paths.get(folder.getAbsolutePath());
			DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
			return !ds.iterator().hasNext();
		} catch (IOException | InvalidPathException e) {
			return false;
		}
	}

	public static ArrayList<File> toArrayList(File[] args) {
		ArrayList<File> returnValue = new ArrayList<>();
		for (File file : args) {
			returnValue.add(file);
		}
		return returnValue;
	}

	public static File[] getIncludedFiles(File[] args) {
		ArrayList<File> includedFs = new ArrayList<File>();
		for (File temp : args) {
			if (isInclusive(temp) && !isCPL(temp)) {
				includedFs.add(temp);
			}
		}
		File[] rV = (File[]) includedFs.toArray(new File[includedFs.size()]);
		return rV;
	}

	public static boolean isCPL(File file) {
		if (file.toString().endsWith("lnk")) {
			return true;
		}
		return false;
	}

	public static boolean isInList(ArrayList<String> list, String arg) {
		for (String extension : list) {
			if (arg.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		return false;
	}

	public static String shortenName(String arg) {
		String returnValue = arg;
		if (arg.length() > 20) {
			int length = returnValue.length();
			String t1 = returnValue.substring(0, 10);
			String t2 = "...";
			String t3 = returnValue.substring(length - 3);
			returnValue = t1 + t2 + t3;
		}

		return returnValue;
	}

	public static String formName(File file) {
		String returnValue = file.toString();
		if (returnValue.contains("\\")) {
			String temp = file.toString();
			returnValue = temp.substring(temp.lastIndexOf("\\") + 1);
		} else {
			returnValue = file.toString();
		}
		returnValue = shortenName(returnValue);

		return returnValue;
	}

	public static boolean isInclusive(File file) {
		boolean returnValue = file.isDirectory() || isMediaFile(file);
		return returnValue;
	}

	public static boolean isAudioFile(File file) {
		boolean returnValue = false;
		ArrayList<String> extensions = new ArrayList<>();
		extensions.add("mp3");
		extensions.add("wav");
		extensions.add("aac");

		if (isInList(extensions, getExtension(file))) {
			returnValue = true;
		}

		return returnValue;
	}

	public static boolean isVideoFile(File file) {
		boolean returnValue = false;
		ArrayList<String> extensions = new ArrayList<>();
		extensions.add("mp4");
		extensions.add("mkv");
		extensions.add("avi");
		extensions.add("mov");

		if (isInList(extensions, getExtension(file))) {
			returnValue = true;
		}

		return returnValue;
	}

	public static boolean isImageFile(File file) {
		boolean returnValue = false;
		ArrayList<String> extensions = new ArrayList<>();
		extensions.add("png");
		extensions.add("jpg");
		extensions.add("jpeg");
		extensions.add("bmp");
		extensions.add("wbmp");
		extensions.add("jpe");
		extensions.add("jfif");
		extensions.add("gif");

		if (isInList(extensions, getExtension(file))) {
			returnValue = true;
		}

		return returnValue;
	}

	public static String getExtension(File file) {
		String fileName = file.toString();
		String returnValue = null;
		if (fileName.contains("\\")) {
			returnValue = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return returnValue;
	}

	public static boolean isMediaFile(File file) {
		boolean returnValue = false;
		try {
			if (isAudioFile(file) || isVideoFile(file) || isImageFile(file)) {
				returnValue = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public static int getWidth(JPanel pane) {
		Component[] cpnts = pane.getComponents();
		ArrayList<Integer> indents = new ArrayList<Integer>();
		for (Component comp : cpnts) {
			try {
				JPane pnl = (JPane) comp;
				indents.add(pnl.getX() + pnl.getWidth());
			} catch (Exception e) {
				continue;
			}
		}
		int maxWidth = Collections.max(indents);
		int height = 10 + maxWidth;
		return height;
	}

	public static Color getRandomColor() {
		int r = (int) (255 * Math.random());
		int g = (int) (255 * Math.random());
		int b = (int) (255 * Math.random());
		return new Color(r, g, b);
	}

	public static ArrayList<JPane> getExpandedC(JPanel contentPane, JPane parent) {
		return null;
	}

	public static int getHeight(JPanel pane) {
		Component[] cpnts = pane.getComponents();
		ArrayList<Integer> depths = new ArrayList<Integer>();
		for (Component comp : cpnts) {
			try {
				JPane pnl = (JPane) comp;
				depths.add(pnl.getY() + pnl.getHeight());
			} catch (Exception e) {
				continue;
			}
		}
		int maxHeight = Collections.max(depths);
		int height = 100 + maxHeight;
		return height;
	}
}
