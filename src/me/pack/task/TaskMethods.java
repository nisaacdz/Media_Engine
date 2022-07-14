package me.pack.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class TaskMethods {

	public static void addListenerToCaret(JTextField textArea) {
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				textArea.getCaret().setVisible(true);
			}
		});
	}

	public static String getProjectVersionName() {
		return "MEDIA_ENGINE 1.0.0";
	}

	public static int getType(File arg) {
		if (arg.isDirectory()) {
			return 0;
		} else if (isImageFile(arg)) {
			return 1;
		} else if (isAudioFile(arg)) {
			return 2;
		} else if (isVideoFile(arg)) {
			return 3;
		} else {
			return 10;
		}
	}

	public static String getFileFormat(File args) {
		if (args.isDirectory()) {
			return "Directory";
		}
		String ext = getExtension(args);
		if (ext.equalsIgnoreCase("jpeg")) {
			return "JPEG image .jpeg";
		} else if (ext.equalsIgnoreCase("jpg")) {
			return "JPEG image .jpg";
		} else if (ext.equalsIgnoreCase("png")) {
			return "PNG image .png";
		} else if (ext.equalsIgnoreCase("mp4")) {
			return "MPEG video .mp4";
		} else if (ext.equalsIgnoreCase("mp3")) {
			return "MPEG audio .mp3";
		} else if (ext.equalsIgnoreCase("jpe")) {
			return "JPEG image .jpe";
		} else if (ext.equalsIgnoreCase("jfif")) {
			return "JPEG image .jfif";
		} else if (ext.equalsIgnoreCase("bmp")) {
			return "Multichrome bitmap .bmp";
		} else if (ext.equalsIgnoreCase("dib")) {
			return "Multichrome bitmap .dib";
		} else if (ext.equalsIgnoreCase("gif")) {
			return "GIF image .gif";
		} else if (ext.equalsIgnoreCase("tif")) {
			return "TIFF image .tif";
		} else if (ext.equalsIgnoreCase("tiff")) {
			return "TIFF image .tiff";
		} else if (ext.equalsIgnoreCase("mkv")) {
			return "New Video Format .mkv";
		} else {
			return "Unknown format";
		}
	}

	public static String getScaledSize(File file) throws IOException {
		String s1;
		if (file.isDirectory()) {
			s1 = Long.toString(TaskMethods.getFolderSize(file));
		} else {
			s1 = Long.toString(file.length());
		}
		String s2 = "bytes";
		if (Double.parseDouble(s1) < 1000) {
			s2 = "bytes";
		}
		if (Double.parseDouble(s1) >= 1000) {
			s2 = "kilobytes";
			s1 = String.valueOf(Double.parseDouble(s1) / 1024);
			if (!s1.contains(".")) {
				s1 = new StringBuilder(s1).append(".000").toString();
			} else {
				s1 = new StringBuilder(s1).append("00").toString();
			}
			s1 = s1.substring(0, s1.indexOf(".") + 3);
		}
		if (Double.parseDouble(s1) > 1000) {
			s2 = "megabytes";
			s1 = String.valueOf(Double.parseDouble(s1) / 1024);
			if (!s1.contains(".")) {
				s1 = new StringBuilder(s1).append(".000").toString();
			}
			s1 = s1.substring(0, s1.indexOf(".") + 3);
		}
		if (Double.parseDouble(s1) > 1000) {
			s2 = "gigabytes";
			s1 = String.valueOf(Double.parseDouble(s1) / 1024);
			if (!s1.contains(".")) {
				s1 = new StringBuilder(s1).append(".000").toString();
			}
			s1 = s1.substring(0, s1.indexOf(".") + 3);
		}
		if (Double.parseDouble(s1) > 1000) {
			s2 = "terabytes";
			s1 = String.valueOf(Double.parseDouble(s1) / 1024);
			if (!s1.contains(".")) {
				s1 = new StringBuilder(s1).append(".000").toString();
			}
			s1 = s1.substring(0, s1.indexOf(".") + 3);
		}
		return s1 + " " + s2;
	}

	public static long getFolderSize(File folder) {
		Path dir = folder.toPath();
		final AtomicLong size = new AtomicLong(0);

		try {
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
					size.addAndGet(attr.size());
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) {
					System.out.println("Skipped : " + file + " (" + exc + ")");
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
					if (exc != null) {
						System.out.println("Traverse problem : " + dir + " (" + exc + ")");
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			throw new AssertionError("statement");
		}
		return size.get();
	}

	public static String getExtension(File file) {
		String fileName = file.toString();
		String returnValue = null;
		if (fileName.contains("\\")) {
			returnValue = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return returnValue;
	}

	public static boolean isInList(ArrayList<String> list, String arg) {
		for (String extension : list) {
			if (arg.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		return false;
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
}