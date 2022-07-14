package me.pack.lookandfeel.active;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.pack.lookandfeel.View;

public class PlayingImage {

	private static Color color = new Color(95, 70, 15);

	private static JPanel newPanel;
	private static JFrame mainFrame;
	private static JPanel originalPanel;

	public static void play(BufferedImage image, String name) {

		originalPanel = View.getMainPanel();

		int x = originalPanel.getX();
		int y = originalPanel.getY();
		int width = originalPanel.getWidth();
		int height = originalPanel.getHeight();

		newPanel = new JPanel();
		newPanel.setBounds(x, y, width, height);
		newPanel.setLayout(null);
		newPanel.setBackground(Color.WHITE);
		originalPanel.setVisible(false);
		mainFrame = View.getMainFrame();
		mainFrame.getContentPane().add(newPanel);

		addControlsPanel(newPanel);
		addHomePanel(newPanel, name);
		createImagePanel(newPanel, image);
	}

	private static void createImagePanel(JPanel newPanel, BufferedImage image) {
		int x = 0;
		int y = 50;
		int width = newPanel.getWidth();
		int height = newPanel.getHeight() - 180;

		double Wratio = (double) image.getWidth() / image.getHeight();
		double Hratio = (double) image.getHeight() / image.getWidth();

		JPanel imageP = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int H = image.getHeight();
				int W = image.getWidth();

				int ex = (width - W) / 2;
				int wy = (height - H) / 2;

				if (W > width) {
					W = width;
					H = (int) (Hratio * W);
					ex = (width - W) / 2;
					wy = (height - H) / 2;

					if (H > height) {
						H = height;
						W = (int) (Wratio * H);
						ex = (width - W) / 2;
						wy = (height - H) / 2;
					}
				}

				else if (H > height) {
					H = height;
					W = (int) (Wratio * H);
					ex = (width - W) / 2;
					wy = (height - H) / 2;

					if (W > width) {
						W = width;
						H = (int) (Hratio * W);
						ex = (width - W) / 2;
						wy = (height - H) / 2;
					}
				}

				g.drawImage(image, ex, wy, W, H, null);
			}

		};
		imageP.setBounds(x, y, width, height);
		imageP.setLayout(null);
		imageP.setBackground(Color.WHITE);
		newPanel.add(imageP);
	}

	private static void addHomePanel(JPanel newPanel, String name) {
		int x = 20;
		int y = 5;
		int width = newPanel.getWidth() - 40;
		int height = 40;
		JPanel homePanel = new JPanel();
		homePanel.setBounds(x, y, width, height);
		homePanel.setLayout(null);
		homePanel.setBackground(color);

		JButton back = new JButton("back");
		back.setBounds(5, 5, 90, 30);
		homePanel.add(back);

		JLabel imageName = new JLabel();
		imageName.setBounds(110, 5, width - 360, 30);
		imageName.setForeground(Color.YELLOW);
		imageName.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		imageName.setText(name);
		homePanel.add(imageName);

		JButton antiC = new JButton("))");
		antiC.setBounds(width - 240, 5, 60, 30);
		homePanel.add(antiC);

		JButton antiN = new JButton("((");
		antiN.setBounds(width - 160, 5, 60, 30);
		homePanel.add(antiN);

		addHomeButtonListener(back);
		newPanel.add(homePanel);
	}

	private static void addControlsPanel(JPanel newPanel) {
		int x = (newPanel.getWidth() - 500) / 2;
		int y = newPanel.getHeight() - 100;
		int width = 500;
		int height = 40;

		JPanel controlsPanel = new JPanel();
		controlsPanel.setBounds(x, y, width, height);
		controlsPanel.setLayout(null);
		decorateControls(controlsPanel);
		controlsPanel.setBackground(color);
		newPanel.add(controlsPanel);
	}

	private static void decorateControls(JPanel controlsPanel) {

		JButton previous = new JButton("<<");
		previous.setBounds(10, 5, 80, 30);
		controlsPanel.add(previous);

		JButton next = new JButton(">>");
		next.setBounds(100, 5, 80, 30);
		controlsPanel.add(next);

		JSlider slider = new JSlider();
		slider.setBounds(190, 5, 300, 30);
		controlsPanel.add(slider);

		addControlsListeners(previous, next, slider);
	}

	private static void addHomeButtonListener(JButton home) {
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newPanel.setVisible(false);
				newPanel.removeAll();
				mainFrame.getContentPane().remove(newPanel);
				originalPanel.setVisible(true);
			}
		});
	}

	private static void addControlsListeners(JButton previous, JButton next, JSlider slider) {
		previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

			}
		});

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {

			}
		});
	}
}