package me.pack.lookandfeel.explorer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPane extends JPanel {

	private File file;
	private JButton nodeButton;
	private JPanel nodePanel;
	private JTextField label;

	private ArrayList<JPane> children = new ArrayList<JPane>();
	private boolean expanded = false;

	private static Color color = new Color(90, 70, 15);
	private static Color newColor = new Color(255, 250, 245);

	private int H;
	private int i;

	private static final long serialVersionUID = 1L;

	public JPane(File args) {
		super();
		file = args;
		addNodeText();

		nodePanel.setVisible(true);
		nodeButton.setVisible(true);
	}

	private void addNodeText() {
		nodeButton = new JButton() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				File imageFile = new File("node.png");
				Image image;
				int width = this.getWidth();
				int height = this.getHeight();
				try {
					image = ImageIO.read(imageFile);
					g.drawImage(image, 0, 0, width, height, null);
				} catch (IOException e) {
					System.out.println("IOException thrown");
				}
			}
		};

		label = new JTextField(Fxns.formName(file));

		nodePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				label.setFont(new Font("Lucida Grande", Font.BOLD, 15));
				label.setEditable(false);
				label.setBackground(newColor);
				label.setForeground(Color.BLACK);
				this.add(label);
			}
		};
		nodePanel.setLayout(null);
		nodePanel.setBorder(BorderFactory.createLineBorder(color));

		this.setLayout(null);
		this.add(nodeButton);
		this.add(nodePanel);
	}

	public int getMediaFileType() {
		if (Fxns.isVideoFile(this.getFile())) {
			return 1;
		} else if (Fxns.isAudioFile(this.getFile())) {
			return 2;
		} else if (Fxns.isImageFile(this.getFile())) {
			return 3;
		} else {
			return 0;
		}
	}

	public boolean isRightFile() {
		boolean returnValue = false;
		if (Fxns.isMediaFile(this.getFile())) {
			returnValue = true;
		}
		return returnValue;
	}

	public boolean isExpandableFolder() {
		boolean returnValue = false;
		if (this.getFile().isDirectory() && !Fxns.dirIsEmpty(this.getFile())) {
			returnValue = true;
		}
		return returnValue;
	}

	public void setExpansionHeight(int arg0) {
		H = arg0;
	}

	public int getExpansionHeight() {
		return H;
	}

	public void setExpansionSize(int arg0) {
		i = arg0;
	}

	public int getExpansionSize() {
		return i;
	}

	public void addChildren(JPane child) {
		children.add(child);
	}

	public ArrayList<JPane> getChildren() {
		return children;
	}

	public void setBoundary(int x, int y, int width, int height) {
		this.setBounds(x, y, width, height);
		nodeButton.setBounds(0, 10, 10, 10);
		nodePanel.setBounds(20, 0, width - 50, height);
		label.setSize(width - 50, height);
	}

	public boolean isBelowExpanded(JPane expanded) {
		if (this.getY() > expanded.getY()) {
			return true;
		}
		return false;
	}

	public JButton getNodeButton() {
		return nodeButton;
	}

	public JPanel getNodePanel() {
		return nodePanel;
	}

	public JTextField getNodeLabel() {
		return label;
	}

	public File getFile() {
		return file;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean args) {
		expanded = args;
	}

}