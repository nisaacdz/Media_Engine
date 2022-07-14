package me.pack.lookandfeel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import me.pack.lookandfeel.domant.Panel;
import me.pack.lookandfeel.domant.panes.DecorateMB;
import me.pack.lookandfeel.domant.panes.pp.DecoratePP;

public class View {

	static Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
	public static final long serialVersionUID = 1L;
	private static Color color = new Color(95, 70, 15);

	private static JFrame Mframe;
	private static JMenuBar bar;

	private static Image icon;

	public static void call() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame mainFrame = new JFrame();
					act000(mainFrame);
					mainFrame.setTitle(Acts.getProjectVersionName());
					initialize(mainFrame);
					Panel.addComponents(mainFrame.getContentPane());
					Mframe = mainFrame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static JFrame getMainFrame() {
		return Mframe;
	}

	public static JPanel getMainPanel() {
		return Panel.getPanel();
	}

	public static JPanel getImagesPane() {
		return DecoratePP.getImagesPane();
	}

	public static JPanel getAudiosPane() {
		return DecoratePP.getAudiosPane();
	}

	public static JPanel getVideosPane() {
		return DecoratePP.getVideosPane();
	}

	private static void act000(JFrame mainFrame) {
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(fullScreen);
	}

	private static void initialize(JFrame mainFrame) {
		try {
			icon = ImageIO.read(new File("iconimage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainFrame.setIconImage(icon);
		mainFrame.setLayout(null);
		mainFrame.getContentPane().add(addMenuBar());
	}

	public static JMenuBar getMenuBar() {
		return bar;
	}

	private static JMenuBar addMenuBar() {
		bar = new JMenuBar();
		int x = 0;
		int y = 0;
		int width = fullScreen.width;
		int height = fullScreen.height / 25;
		bar.setBounds(x, y, width, height);
		bar.setBackground(color);
		DecorateMB.perform(bar);
		return bar;
	}
}