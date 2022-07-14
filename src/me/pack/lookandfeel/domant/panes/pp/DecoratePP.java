package me.pack.lookandfeel.domant.panes.pp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import me.pack.task.MButtonListener;

public class DecoratePP {

	private static JPanel recentVideos;
	private static JPanel recentAudios;
	private static JPanel recentImages;

	private static Border border1;
	private static Border borderB;

	private static int Twidth;
	private static int Theight;

	private static Color color = new Color(90, 70, 15);

	public static void events(JPanel panel) {
		createPanels(panel.getSize());
		decoratePanels();
		addPanels(panel);
		ThumbsPane.events();
	}

	private static void createPanels(Dimension d) {
		Twidth = d.width;
		Theight = d.height - 35;

		tools();

		recentVideos = new JPanel();
		recentAudios = new JPanel();
		recentImages = new JPanel();
	}

	private static void tools() {
		int arg1 = 2;
		border1 = BorderFactory.createLineBorder(color, arg1);
		borderB = BorderFactory.createRaisedBevelBorder();
	}

	private static void decoratePanels() {
		recentImages.setBounds(0, 0, Twidth, Theight / 3);
		recentImages.setBackground(Color.WHITE);
		recentImages.setBorder(border1);
		recentImages.setLayout(null);
		subRecentImages();

		recentAudios.setBounds(0, Theight / 3, Twidth, Theight / 3);
		recentAudios.setBackground(Color.WHITE);
		recentAudios.setBorder(border1);
		recentAudios.setLayout(null);
		subRecentAudios();

		recentVideos.setBounds(0, 2 * Theight / 3, Twidth, Theight / 3);
		recentVideos.setBackground(Color.WHITE);
		recentVideos.setBorder(border1);
		recentVideos.setLayout(null);
		subRecentVideos();
	}

	private static void addPanels(JPanel panel) {
		panel.add(recentVideos);
		panel.add(recentAudios);
		panel.add(recentImages);
	}

	private static void subRecentVideos() {
		int x1 = 5;
		int y1 = 0;
		int width1 = recentVideos.getWidth() / 3;
		int height1 = 20;

		int x2 = 23 * recentVideos.getWidth() / 25;
		int y2 = 2;
		int width2 = 2 * recentVideos.getWidth() / 25;
		int height2 = 20;

		JLabel rV = new JLabel();
		rV.setBounds(x1, y1, width1, height1);
		rV.setText("Recent Videos");
		rV.setFont(new Font("Sylfaen", Font.BOLD, 18));
		rV.setForeground(color);
		recentVideos.add(rV);

		JButton mV = new JButton();
		mV.setBounds(x2, y2, width2, height2);
		mV.setBackground(color);
		mV.setForeground(Color.YELLOW);
		mV.setBorder(borderB);
		mV.setText(">>>");
		MButtonListener.moreVideosWaiter(mV);
		recentVideos.add(mV);
	}

	private static void subRecentAudios() {
		int x1 = 5;
		int y1 = 0;
		int width1 = recentAudios.getWidth() / 3;
		int height1 = 20;

		int x2 = 23 * recentAudios.getWidth() / 25;
		int y2 = 2;
		int width2 = 2 * recentAudios.getWidth() / 25;
		int height2 = 20;

		JLabel rA = new JLabel();
		rA.setBounds(x1, y1, width1, height1);
		rA.setText("Recent Audios");
		rA.setFont(new Font("Sylfaen", Font.BOLD, 18));
		rA.setForeground(color);
		recentAudios.add(rA);

		JButton mA = new JButton();
		mA.setBounds(x2, y2, width2, height2);
		mA.setBackground(color);
		mA.setForeground(Color.YELLOW);
		mA.setBorder(borderB);
		mA.setText(">>>");
		MButtonListener.moreAudiosWaiter(mA);
		recentAudios.add(mA);
	}

	private static void subRecentImages() {
		int x1 = 5;
		int y1 = 0;
		int width1 = recentImages.getWidth() / 3;
		int height1 = 20;

		int x2 = 23 * recentImages.getWidth() / 25;
		int y2 = 2;
		int width2 = 2 * recentImages.getWidth() / 25;
		int height2 = 20;

		JLabel rI = new JLabel();
		rI.setBounds(x1, y1, width1, height1);
		rI.setText("Recent Images");
		rI.setFont(new Font("Sylfaen", Font.BOLD, 18));
		rI.setForeground(color);
		recentImages.add(rI);

		JButton mI = new JButton();
		mI.setBounds(x2, y2, width2, height2);
		mI.setBackground(color);
		mI.setForeground(Color.YELLOW);
		mI.setBorder(borderB);
		mI.setText(">>>");
		MButtonListener.moreImagesWaiter(mI);
		recentImages.add(mI);
	}

	public static JPanel getImagesPane() {
		return recentImages;
	}

	public static JPanel getVideosPane() {
		return recentVideos;
	}

	public static JPanel getAudiosPane() {
		return recentAudios;
	}
}
