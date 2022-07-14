package me.pack.lookandfeel.domant.panes.pp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import me.pack.task.ImageTasks;

public class ThumbsMethods {

	protected static void mouseWatcher(JPanel picture, String path) {
		picture.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent a) {
				if (a.getClickCount() == 2) {
					ImageTasks.open(new File(path));
				} else if (SwingUtilities.isRightMouseButton(a)) {
					int x = a.getX();
					int y = a.getY();
					ThumbsMethods.popOutMenu(picture, path).show(picture, x, y);
				} else if (a.getClickCount() == 1) {
					
				}
			}
		});
	}
	
	public static void mouseWatcher(JPanel thumb, String path, JPanel parent) {
		thumb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent a) {
				if (a.getClickCount() == 2) {
					parent.setVisible(false);
					parent.removeAll();
					ImageTasks.open(new File(path));
				} else if (SwingUtilities.isRightMouseButton(a)) {
					int x = a.getX();
					int y = a.getY();
					ThumbsMethods.popOutMenu(thumb, path).show(thumb, x, y);
				} else if (a.getClickCount() == 1) {
					
				}
			}
		});
	}

	protected static JPopupMenu popOutMenu(JPanel picture, String path) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem open = new JMenuItem("Open");
		JMenuItem select = new JMenuItem("Select");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem rename = new JMenuItem("Rename");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem remove = new JMenuItem("Remove from recent");
		JMenuItem details = new JMenuItem("Properties");

		addMenuItemListener(open, path, picture);
		addMenuItemListener(select, path, picture);
		addMenuItemListener(copy, path, picture);
		addMenuItemListener(cut, path, picture);
		addMenuItemListener(rename, path, picture);
		addMenuItemListener(delete, path, picture);
		addMenuItemListener(remove, path, picture);
		addMenuItemListener(details, path, picture);

		menu.add(open);
		menu.add(select);
		menu.add(copy);
		menu.add(cut);
		menu.add(rename);
		menu.add(delete);
		menu.add(remove);
		menu.add(details);

		picture.add(menu);

		return menu;
	}

	private static void addMenuItemListener(JMenuItem menuitem, String path, JPanel picture) {
		menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = ((JMenuItem) e.getSource()).getText();

				switch (source) {
				case "Open":
					ImageTasks.open(new File(path));
					break;
				case "Select":
					ImageTasks.select(new File(path), picture);
					break;
				case "Copy":
					ImageTasks.copy(new File(path), picture);
					break;
				case "Cut":
					ImageTasks.cut(new File(path), picture);
					break;
				case "Rename":
					ImageTasks.rename(new File(path), picture);
					break;
				case "Delete":
					ImageTasks.delete(new File(path));
					break;
				case "Remove from recent":
					ImageTasks.removeFromRecent(new File(path), picture);
					break;
				case "Properties":
					ImageTasks.properties(new File(path), picture);
					break;
				default:
					System.out.println("Other Item Selected");
				}
			}
		});
	}

}
