package me.pack.lookandfeel.domant;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import me.pack.lookandfeel.View;
import me.pack.lookandfeel.domant.panes.DecorateME;
import me.pack.lookandfeel.domant.panes.pp.DecoratePP;

public class Panel {

	static Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();

	private static Color color = new Color(95, 70, 15);

	private static JPanel panel;

	private static JPanel getMainPanel() {
		int x = 1;
		int y = View.getMenuBar().getY() + View.getMenuBar().getHeight();
		int width = fullScreen.width - 10;
		int height = fullScreen.height - y;
		panel = new JPanel();
		panel.setBounds(x, y, width, height);
		panel.setBackground(color);
		panel.setLayout(null);
		return panel;
	}

	private static JPanel addMediaExplorer() {
		JPanel mpanel = new JPanel();
		int width = panel.getWidth() / 4;
		int height = panel.getHeight() - 50;
		mpanel.setSize(width, height);
		mpanel.setLayout(null);
		int line = 5;
		mpanel.setBorder(BorderFactory.createLineBorder(color, line));
		mpanel.setBackground(color);
		DecorateME.events(mpanel);
		return mpanel;
	}

	private static JPanel addPlainPanel() {
		JPanel paneT = new JPanel();
		int x = panel.getWidth() / 4;
		int y = 0;
		int width = 3 * panel.getWidth() / 4;
		int height = panel.getHeight();
		paneT.setBounds(x, y, width, height);
		paneT.setBackground(color);
		paneT.setLayout(null);
		DecoratePP.events(paneT);
		return paneT;
	}

	public static JPanel getPanel() {
		return panel;
	}

	public static void addComponents(Container main) {
		JPanel mainPanel = getMainPanel();
		main.add(mainPanel);
		mainPanel.add(addMediaExplorer());
		mainPanel.add(addPlainPanel());
	}

}
