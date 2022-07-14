package me.pack.lookandfeel.active;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.pack.engine.database.thumbs.ImageThumbsLibrary;
import me.pack.engine.database.thumbs.Thumbnail;
import me.pack.lookandfeel.View;
import me.pack.lookandfeel.domant.panes.pp.ImageThumbsPane;
import me.pack.lookandfeel.domant.panes.pp.ThumbsMethods;

public class FullImageThumbs {
	private static Color color = new Color(95, 70, 15);

	private static JPanel newPanel;
	private static JFrame mainFrame;
	private static JPanel originalPanel;

	private static int twidth, theight, H, V;

	public static void start() {

		originalPanel = View.getMainPanel();

		int x = originalPanel.getX();
		int y = originalPanel.getY();
		int width = originalPanel.getWidth();
		int height = originalPanel.getHeight();

		newPanel = new JPanel();
		newPanel.setVisible(false);
		newPanel.setBounds(x, y, width, height);
		newPanel.setLayout(null);
		newPanel.setBackground(Color.WHITE);

		standardize(newPanel);
		try {
			addThumbs(newPanel);
		} catch (Exception u) {
			u.printStackTrace();
		}

		originalPanel.setVisible(false);
		newPanel.setVisible(true);

		mainFrame = View.getMainFrame();
		mainFrame.getContentPane().add(newPanel);
	}

	private static void standardize(JPanel newPanel) {

		int sqr = View.getImagesPane().getHeight();

		Dimension d = new Dimension(sqr, sqr);

		twidth = d.width + 10;
		theight = d.height + 10;

		H = newPanel.getWidth() / twidth;
		V = newPanel.getHeight() / theight;

		twidth = newPanel.getWidth() / H;
		theight = newPanel.getHeight() / V;

		System.out.println("Width : " + twidth);
		System.out.println("Thumb Width : " + d.width);

		System.out.println("Height : " + theight);
		System.out.println("Thumb Height : " + d.height);

		System.out.println("H : " + H + " , V : " + V);

		JButton back = new JButton("back");
		back.setBounds(10, 10, 75, 20);
		back.setBackground(color);
		back.setForeground(Color.YELLOW);
		addHomeButtonListener(back);

		newPanel.add(back);
	}

	@SuppressWarnings("unchecked")
	private static void addThumbs(JPanel newPanel) throws Exception {
		File metaF = ImageThumbsLibrary.getArchiveFile();

		FileInputStream fis = new FileInputStream(metaF);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Thumbnail> metaMap = (ArrayList<Thumbnail>) ois.readObject();
		ois.close();

		int tx = 5;
		int ty = 0;
		int tw = twidth - 10;
		int th = theight - 10;

		int i = 1;
		for (Thumbnail n : metaMap) {
			drawThumb(n.getThumbImage(), tx, ty, tw, th, n.getPath());

			tx += twidth;
			i++;
			if (i > H) {
				i = 1;
				tx = 5;
				ty += theight;
			}
		}
	}

	private static void drawThumb(Image img, int tx, int ty, int tw, int th, String path) {
		JPanel thumb = new JPanel() {

			private static final long serialVersionUID = 1L;

			String fileName = new File(path).getName();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				addLabel(this);
				g.dispose();
			}

			public void addLabel(JPanel picture) {
				JTextField label = new JTextField(fileName);
				label.setBounds(5, th - 50, tw - 10, 45);
				label.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label.setEditable(false);
				label.setForeground(Color.YELLOW);
				label.setBackground(new Color(0, 0, 0, 0));
				label.setBorder(null);

				picture.add(label);
			}
		};

		thumb.setBounds(tx, ty, tw, th);
		thumb.setLayout(null);
		ThumbsMethods.mouseWatcher(thumb, path, newPanel);
		newPanel.add(thumb);
	}

	private static void addHomeButtonListener(JButton home) {
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newPanel.setVisible(false);
				newPanel.removeAll();
				mainFrame.getContentPane().remove(newPanel);
				originalPanel.setVisible(true);
				try {
					ImageThumbsLibrary.updateArchive();
					ImageThumbsPane.updateThumbs(ImageThumbsLibrary.getArchiveFile());
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
	}
}
