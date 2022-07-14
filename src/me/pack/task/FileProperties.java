package me.pack.task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class FileProperties {

	private static JDialog details;
	private static int width = 350;
	private static int height = 230;

	private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	private static Color color = new Color(90, 70, 15);
	private static Color newColor = new Color(255, 250, 245);

	public FileProperties(File file) {

		UIManager.put("activeCaption", new ColorUIResource(color));
		UIManager.put("activeCaptionText", new ColorUIResource(Color.YELLOW));
		JDialog.setDefaultLookAndFeelDecorated(true);

		int x = (d.width - width) / 2;
		int y = (d.height - height) / 2;

		details = new JDialog();
		details.setTitle(TaskMethods.getProjectVersionName());
		details.setBounds(x, y, width, height);
		details.setBackground(newColor);
		details.setLayout(null);
		details.setVisible(true);

		decorate(file);
		addListener(details);
	}

	private void addListener(JDialog details) {
		details.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				details.setVisible(false);
				details.removeAll();
			}
		});
	}

	public JDialog getDialog() {
		return details;
	}

	private static void decorate(File file) {
		try {
			setTitle(file);
			setFileType(file);
			setSize(file);
			setAbsoluteSize(file);
			setLocation(file);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	private static void setLocation(File file) {
		int x = 10;
		int y = 160;
		int width = 100;
		int height = 20;

		JLabel src = new JLabel("Location on disk");
		src.setBounds(x, y, width, height);

		JTextField path = new JTextField();
		path.setBounds(x + 110, y, details.getWidth() - width - 40, height);
		path.setText(file.getAbsolutePath());
		path.setEditable(false);
		TaskMethods.addListenerToCaret(path);

		details.add(src);
		details.add(path);
	}

	public static void setAbsoluteSize(File file) throws IOException {
		int x = 10;
		int y = 130;
		int width = 130;
		int height = 20;
		JLabel info = new JLabel("Absolute Size of file");
		info.setBounds(x, y, width, height);

		JTextField size = new JTextField();
		size.setBounds(x + 140, y, details.getWidth() - width - 40, height);
		size.setText(TaskMethods.getScaledSize(file));
		size.setEditable(false);

		details.add(info);
		details.add(size);
	}

	public static void setFileType(File file) {
		int x = 10;
		int y = 70;
		int width = 50;
		int height = 20;
		JLabel type = new JLabel("File type");
		type.setBounds(x, y, width, height);

		JTextField fileFormat = new JTextField();
		fileFormat.setBounds(x + 60, y, details.getWidth() - width - 40, height);
		fileFormat.setText(TaskMethods.getFileFormat(file));
		fileFormat.setEditable(false);

		details.add(type);
		details.add(fileFormat);
	}

	public static void setSize(File file) throws IOException {
		int x = 10;
		int y = 100;
		int width = 130;
		int height = 20;

		JLabel label = new JLabel("Space allocated to file");
		label.setBounds(x, y, width, height);

		JTextField space = new JTextField();
		space.setBounds(x + 140, y, details.getWidth() - width - 40, height);
		space.setText(TaskMethods.getScaledSize(file));
		space.setEditable(false);

		details.add(label);
		details.add(space);
	}

	private static void setTitle(File file) {
		int x = 0;
		int y = 15;
		int width = details.getWidth();
		int height = 40;

		BufferedImage image;
		try {
			image = getFileTypeImage(file);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		String name = file.getName();

		JPanel title = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 10, 0, 40, 40, this);
			}
		};
		title.setLayout(null);
		title.setBounds(x, y, width, height);

		JTextField nameA = new JTextField();
		nameA.setBounds(60, 10, width - 80, 20);
		nameA.setText(name);
		nameA.setEditable(false);
		TaskMethods.addListenerToCaret(nameA);

		title.add(nameA);
		details.add(title);
	}

	private static BufferedImage getFileTypeImage(File file) throws IOException {
		BufferedImage returnValue;
		int type = TaskMethods.getType(file);

		switch (type) {
		case 0:
			returnValue = ImageIO.read(new File("media/folder.png"));
			break;
		case 1:
			returnValue = ImageIO.read(new File("media/image.png"));
			break;
		case 2:
			returnValue = ImageIO.read(new File("media/audio.png"));
			break;
		case 3:
			returnValue = ImageIO.read(new File("media/video.png"));
			break;
		default:
			returnValue = null;
		}
		return returnValue;
	}
}