package me.pack.task;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import me.pack.engine.database.thumbs.ImageThumbsLibrary;
import me.pack.engine.players.PlayImage;
import me.pack.lookandfeel.active.PlayingImage;
import me.pack.lookandfeel.domant.panes.pp.ImageThumbsPane;

public class ImageTasks {

	public static void open(File ifile) {
		BufferedImage image = null;
		try {
			image = PlayImage.display(ifile);
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		PlayingImage.play(image, ifile.getName());
		addThumbs(ifile);
	}

	private static void addThumbs(File ifile) {
		Dimension size = ImageThumbsPane.getThumbSize();
		File metaMap;
		try {
			metaMap = ImageThumbsLibrary.browseThumbnails(ifile, size);
			ImageThumbsPane.updateThumbs(metaMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deselect(File file, JPanel pane) {
		
	}

	public static void select(File file, JPanel pane) {
		Tasks.select(file, pane);
	}

	public static int paste(File arg) {
		return Tasks.paste(arg);
	}

	public static void cut(File file, JPanel pane) {
		Tasks.cut(file, pane);
	}

	public static void copy(File file, JPanel pane) {
		Tasks.copy(file, pane);
	}

	public static void rename(File file, JPanel pane) {
		
	}

	public static void removeFromRecent(File file, JPanel pane) {
		Tasks.removeFromRecent(file, pane);
	}

	public static int delete(File file) {
		return Tasks.delete(file);
	}

	public static void properties(File file, JPanel pane) {
		Tasks.properties(file, pane);
	}

	//////////////////
	////////
	//////////
	/////////////////
	/////////////////
	//////////////////
	//////////////
	/////////

}
