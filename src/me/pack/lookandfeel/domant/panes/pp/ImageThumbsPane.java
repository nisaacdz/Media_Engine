package me.pack.lookandfeel.domant.panes.pp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import me.pack.engine.database.thumbs.ImageThumbsLibrary;
import me.pack.engine.database.thumbs.Thumbnail;
import me.pack.lookandfeel.View;

public class ImageThumbsPane {

	private static int thumbHeight;
	private static int thumbWidth;
	private static int totalThumbs;

	private static JPanel imageThumbs;

	public static void addImageThumbs(JPanel thumbsPanel) throws Exception {

		if (ImageThumbsLibrary.getArchiveFile().exists()) {
			ImageThumbsLibrary.updateArchive();
		}

		JPanel ipanel = View.getImagesPane();
		int height = ipanel.getHeight() - 30;

		thumbHeight = height;

		thumbWidth = thumbHeight;
		totalThumbs = thumbsPanel.getWidth() / thumbWidth + 1;

		File ithumbsFile = ImageThumbsLibrary.getArchiveFile();

		if (ithumbsFile.exists()) {
			updateThumbs(ithumbsFile);
		}
	}

	@SuppressWarnings("unchecked")
	public static void updateThumbs(File metaImage) throws Exception {
		imageThumbs = ThumbsPane.getImageThumbsPane();

		imageThumbs.setVisible(false);
		imageThumbs.removeAll();

		// Deserialize
		FileInputStream fis = new FileInputStream(metaImage);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Thumbnail> metaMap = (ArrayList<Thumbnail>) ois.readObject();
		ois.close();

		if (totalThumbs < metaMap.size()) {
			for (int i = 0; i < totalThumbs; i++) {
				Thumbnail tempnail = metaMap.get(i);
				createAt(i, tempnail.getThumbImage(), tempnail.getPath(), imageThumbs);
			}
		} else {
			for (int i = 0; i < metaMap.size(); i++) {
				Thumbnail tempnail = metaMap.get(i);
				createAt(i, tempnail.getThumbImage(), tempnail.getPath(), imageThumbs);
			}
		}
		imageThumbs.setVisible(true);
	}

	private static void createAt(int index, Image im, String path, JPanel pane) {
		int x = index * (thumbWidth + 5);
		int y = 0;
		int width = thumbWidth;
		int height = thumbHeight;

		JPanel picture = new JPanel() {
			private static final long serialVersionUID = 1L;

			String fileName = new File(path).getName();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(im, 0, 0, width, height, null);

				addLabel(this);

				g.dispose();
			}

			public void addLabel(JPanel picture) {
				JTextField label = new JTextField(fileName);
				label.setBounds(5, height - 50, width - 10, 45);
				label.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label.setEditable(false);
				label.setForeground(Color.YELLOW);
				label.setBackground(new Color(0, 0, 0, 0));
				label.setBorder(null);

				picture.add(label);
			}
		};

		picture.setLayout(null);
		picture.setBounds(x, y, width, height);
		ThumbsMethods.mouseWatcher(picture, path);

		pane.add(picture);
	}

	public static Dimension getThumbSize() {
		return new Dimension(thumbWidth, thumbHeight);
	}

	public static int getTotalThumbs() {
		return totalThumbs;
	}
}
