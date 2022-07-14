package me.pack.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;

import me.pack.engine.database.thumbs.ImageThumbsLibrary;
import me.pack.engine.database.thumbs.Thumbnail;
import me.pack.lookandfeel.domant.panes.pp.ImageThumbsPane;

public class Tasks {

	private static String separator = File.separator;

	private static ArrayList<File> clipboard = new ArrayList<File>();
	private static ArrayList<File> selection = new ArrayList<File>();

	private static int clipboardType;
	private static final int COPY = 4;
	private static final int CUT = 3;

	public static void deselect(File file, JPanel pane) {

	}

	public static void select(File file, JPanel pane) {
		selection.add(file);
	}

	public static int paste(File arg) {
		String directory = "thumbs";
		if (arg.isDirectory()) {
			directory = arg.getAbsoluteFile() + separator;
		} else if (arg.isFile()) {
			directory = arg.getParent() + separator;
		}

		if (getClipboard() == CUT) {
			ArrayList<File> clipbase = new ArrayList<File>();
			for (File f : clipboard) {
				File newFile = new File(directory + f.getName());
				f.renameTo(newFile);
				clipbase.add(newFile);
			}
			clipboard = clipbase;
			setClipboard(COPY);
		} else if (getClipboard() == COPY) {
			for (File f : clipboard) {
				try {
					Files.copy(f.toPath(), new File(directory + f.getName()).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			ImageThumbsLibrary.updateArchive();
			ImageThumbsPane.updateThumbs(ImageThumbsLibrary.getArchiveFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clipboard.size();
	}

	public static void cut(File file, JPanel pane) {
		if (selection.size() == 0) {
			select(file, pane);
			setClipboard(CUT);
			clipboard = selection;
			selection = new ArrayList<File>();
		} else if (selection.size() > 0) {
			setClipboard(CUT);
			clipboard = selection;
			selection = new ArrayList<File>();
		}
	}

	public static void copy(File file, JPanel pane) {
		if (selection.size() == 0) {
			select(file, pane);
			setClipboard(COPY);
			clipboard = selection;
		}
		if (selection.size() > 0) {
			setClipboard(COPY);
			clipboard = selection;
		}
	}

	public static void rename(File file, JPanel pane) {

	}

	@SuppressWarnings("unchecked")
	public static void removeFromRecent(File file, JPanel pane) {
		File metaFile = ImageThumbsLibrary.getArchiveFile();
		ArrayList<Thumbnail> metaMap;

		try {

			FileInputStream fis = new FileInputStream(metaFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			metaMap = (ArrayList<Thumbnail>) ois.readObject();
			ois.close();

			metaMap.remove(new Thumbnail(file.toString()));

			FileOutputStream fos = new FileOutputStream(metaFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(metaMap);
			oos.close();

			ImageThumbsLibrary.updateArchive();
			ImageThumbsPane.updateThumbs(ImageThumbsLibrary.getArchiveFile());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int delete(File file) {
		return 1;
	}

	public static void properties(File file, JPanel panel) {
		select(file, panel);
		JDialog details = new FileProperties(file).getDialog();
		details.setVisible(true);
	}

	//////////////////
	////////
	//////////
	/////////////////
	/////////////////
	//////////////////
	//////////////
	/////////

	private static int getClipboard() {
		return clipboardType;
	}

	private static void setClipboard(int type) {
		clipboardType = type;
	}
}
