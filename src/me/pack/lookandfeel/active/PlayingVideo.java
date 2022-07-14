package me.pack.lookandfeel.active;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import me.pack.lookandfeel.Acts;

public class PlayingVideo {

	static Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();

	public static void play(File vfile) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame vframe = new JFrame(vfile.getName());
				vframe.setSize(fullScreen);

				vframe.setVisible(true);
				vframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				init(vframe);
			}

			public void init(JFrame vframe) {
				vframe.setLayout(null);
				int width = fullScreen.width;
				int height = fullScreen.height;

				int h = 20;
				try {
					for (int x = 0; x < width; x += h) {
						for (int y = 0; y < height; y += h) {
							JPanel pixel = new JPanel();
							pixel.setBounds(x, y, h, h);
							vframe.getContentPane().add(pixel);
							renderBackground(pixel);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void renderBackground(JPanel pixel) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				render(pixel);
			}
		});

		thread.start();
	}
	
	public static void render(JPanel pixel) {
		do {
			pixel.setBackground(Acts.getRandomColor());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}
}