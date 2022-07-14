package me.pack.lookandfeel.domant.panes.pp;

import java.awt.Color;

import javax.swing.JPanel;

import me.pack.lookandfeel.View;

public class ThumbsPane {

	private static JPanel audioThumbs;
	private static JPanel videoThumbs;
	private static JPanel imageThumbs;

	public static void events() {
		initialize();
		try {
			ImageThumbsPane.addImageThumbs(imageThumbs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initialize() {
		JPanel ipanel = View.getImagesPane();

		int x = 10;
		int y = 25;
		int width = ipanel.getWidth() - 20;
		int height = ipanel.getHeight() - 30;

		imageThumbs = new JPanel();
		imageThumbs.setBounds(x, y, width, height);
		imageThumbs.setLayout(null);
		imageThumbs.setBackground(Color.WHITE);
		ipanel.add(imageThumbs);

		JPanel apanel = View.getAudiosPane();

		audioThumbs = new JPanel();
		audioThumbs.setBounds(x, y, width, height);
		audioThumbs.setLayout(null);
		audioThumbs.setBackground(Color.WHITE);
		apanel.add(audioThumbs);

		JPanel vpanel = View.getVideosPane();

		videoThumbs = new JPanel();
		videoThumbs.setBounds(x, y, width, height);
		videoThumbs.setLayout(null);
		videoThumbs.setBackground(Color.WHITE);
		vpanel.add(videoThumbs);
	}

	public static JPanel getImageThumbsPane() {
		return imageThumbs;
	}

	public static JPanel getAudioThumbsPane() {
		return audioThumbs;
	}

	public static JPanel getVideoThumbsPane() {
		return videoThumbs;
	}
}
