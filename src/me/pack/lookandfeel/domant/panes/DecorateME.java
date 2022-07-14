package me.pack.lookandfeel.domant.panes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.JPanel;

import me.pack.engine.Model;
import me.pack.lookandfeel.explorer.MFileTree;

public class DecorateME {

	private static MFileTree tree;

	private static Color color = new Color(95, 70, 15);

	public static void events(JPanel mpanel) {
		int x = 5;
		int y = 5;
		int width = mpanel.getWidth() - 10;
		int height = mpanel.getHeight() - 10;

		Dimension d = new Dimension(width, height);

		File root = Model.getHomeDirectory();

		tree = new MFileTree(root, d);
		tree.setBoundary(x, y, width, height);
		tree.getTreeTitle().setText("Media Explorer");
		tree.getTreeTitle().setForeground(Color.YELLOW);
		tree.getTreeTitle().setFont(new Font("Lucida Grande", Font.BOLD, 18));
		tree.getTreeTitleView().setBackground(color);

		tree.setVisible(true);
		mpanel.add(tree.getContainer());
	}
}