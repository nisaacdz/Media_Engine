package me.pack.engine;

import java.io.File;
import java.util.HashMap;

import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;

public abstract class Model extends SwingWorker<Void, Void> {

	private static File home = FileSystemView.getFileSystemView().getHomeDirectory();
	protected String text;

	public static String getDirectory() {
		String directory = home.getAbsolutePath();
		return directory;
	}

	public static File getHomeDirectory() {
		return home;
	}

	@Override
	protected Void doInBackground() throws Exception {
		return null;
	}

	public abstract HashMap<String, File> getMap();

	public abstract String getText();
}