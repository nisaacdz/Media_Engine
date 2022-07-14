package me.pack.lookandfeel.explorer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import me.pack.task.Tasks;

public class MFileTree {

	private String title = "File Explorer";

	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JPanel ttlPane;
	private JLabel label;
	private JPane jp;
	private JButton paneB;
	private JTextField paneT;
	private File root;

	private int scpWidth;
	private int scpHeight;

	private int ttPW;
	private int ttPH;

	private int jpX;
	private int jpY;
	private int jpW;
	private int jpH;

	private Dimension dimension;

	private Color newColor = new Color(150, 120, 60);
	private Color newColor1 = new Color(255, 250, 245);

	private MFileTree tree;

	public MFileTree(File arg0, Dimension size) {
		tree = this;
		root = arg0;
		dimension = size;

		initialize();
		preSet();
		expandedState1(jp);
	}

	private void initialize() {
		scpWidth = dimension.width;
		scpHeight = dimension.height;

		ttPW = scpWidth;
		ttPH = 55;

		jpX = 10;
		jpY = ttPH + 10;
		jpW = ttPW - 40;
		jpH = 30;

		scrollPane = new JScrollPane();
		contentPane = new JPanel();
		ttlPane = new JPanel();
		label = new JLabel(title);
		jp = new JPane(root);
		paneB = jp.getNodeButton();
		paneT = jp.getNodeLabel();
	}

	private void preSet() {
		scrollPane.setSize(scpWidth, scpHeight - 30);
		int vsb = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int hsb = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		scrollPane.setVerticalScrollBarPolicy(vsb);
		scrollPane.setHorizontalScrollBarPolicy(hsb);

		contentPane.setSize(scrollPane.getX(), scrollPane.getY() - 10);

		Dimension pfrd = new Dimension(scpWidth - 10, scpHeight - 75);
		contentPane.setPreferredSize(pfrd);
		contentPane.setLayout(null);

		label.setBounds(10, ttPH / 6, ttPW, 4 * ttPH / 6);
		label.setFont(new Font("", Font.BOLD, 20));
		label.setBackground(Color.BLACK);
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.LEFT);

		ttlPane.setSize(ttPW, ttPH);
		ttlPane.setBackground(Color.YELLOW);
		ttlPane.setLayout(null);
		ttlPane.add(label);

		jp.setBoundary(jpX, jpY, jpW, jpH);
		jp.setExpanded(true);

		addPaneListeners(paneB, paneT, jp);

		contentPane.add(ttlPane);
		contentPane.add(jp);

		scrollPane.setViewportView(contentPane);
	}

	private void expandedState1(JPane arg0) {
		int paneX = arg0.getX() + 10;
		int paneY = arg0.getY();
		int paneW = arg0.getWidth();
		int paneH = arg0.getHeight();

		if (root.isDirectory() && !Fxns.dirIsEmpty(root)) {
			File[] offsp01 = Fxns.getIncludedFiles(root.listFiles());

			int H = offsp01.length * (arg0.getHeight() + 10);

			arg0.setExpansionSize(offsp01.length);
			arg0.setExpansionHeight(H);

			for (File childF : offsp01) {
				paneY += 40;
				JPane pane = new JPane(childF);
				pane.setBoundary(paneX, paneY, paneW, paneH);

				JButton tempB = pane.getNodeButton();
				JTextField tempT = pane.getNodeLabel();

				addPaneListeners(tempB, tempT, pane);

				arg0.addChildren(pane);

				contentPane.add(pane);
			}
		} else {
		}
	}

	protected void expand(JPane arg0) {
		int paneX = arg0.getX() + 10;
		int paneY = arg0.getY() + 40;
		int paneW = arg0.getWidth();
		int paneH = arg0.getHeight();

		File dir = arg0.getFile();

		if (dir.isDirectory() && !Fxns.dirIsEmpty(dir)) {

			arg0.setExpanded(true);

			File[] fls = Fxns.getIncludedFiles(dir.listFiles());

			int H = fls.length * (arg0.getHeight() + 10);

			arg0.setExpansionSize(fls.length);
			arg0.setExpansionHeight(H);

			createExpansionSpace(arg0, H);
			for (File childF : fls) {
				JPane pane = new JPane(childF);
				pane.setBoundary(paneX, paneY, paneW, paneH);

				JButton tempB = pane.getNodeButton();
				JTextField tempT = pane.getNodeLabel();

				addPaneListeners(tempB, tempT, pane);
				arg0.addChildren(pane);

				contentPane.add(pane);
				paneY += 40;
			}
		} else {
		}

		int width = Fxns.getWidth(contentPane);
		int height = Fxns.getHeight(contentPane);
		Dimension d = new Dimension(width, height);
		contentPane.setPreferredSize(d);
	}

	private void createExpansionSpace(JPane arg0, int arg1) {
		Component[] paneComps = contentPane.getComponents();
		Fxns.setAllInvisible(paneComps);

		int H = arg1;

		for (Component comp : paneComps) {
			JPane parentS;
			try {
				parentS = (JPane) comp;
				if (parentS.isBelowExpanded(arg0)) {
					int x = parentS.getX();
					int y = parentS.getY() + H;
					int width = parentS.getWidth();
					int height = parentS.getHeight();
					parentS.setBounds(x, y, width, height);
				}
			} catch (Exception e) {
				continue;
			}
		}
		Fxns.setAllVisible(paneComps);
	}

	private void collapse(JPane arg0) {

		int loopSize = arg0.getExpansionSize();

		int x = arg0.getX() + 10;
		int y = arg0.getY();

		arg0.setExpanded(false);

		JPane gabbage;

		for (int i = 0; i < loopSize; i++) {
			try {
				y += 40;
				gabbage = (JPane) contentPane.getComponentAt(x, y);
				gabbage.setVisible(false);
				contentPane.remove(gabbage);
				gabbage = null;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}

		int arg = arg0.getExpansionHeight();

		consumeCollapsedSpace(arg0, arg);

		int width = Fxns.getWidth(contentPane);
		int height = Fxns.getHeight(contentPane);
		Dimension d = new Dimension(width, height);
		contentPane.setPreferredSize(d);
	}

	private void consumeCollapsedSpace(JPane arg0, int heightC) {
		Component[] paneComps = contentPane.getComponents();
		Fxns.setAllInvisible(paneComps);

		for (Component comp : paneComps) {
			JPane parentS;
			try {
				parentS = (JPane) comp;
				if ((parentS.getY() > 0) && parentS.isBelowExpanded(arg0)) {
					int x = parentS.getX();
					int y = parentS.getY() - heightC;
					int width = parentS.getWidth();
					int height = parentS.getHeight();
					parentS.setBounds(x, y, width, height);
				}
			} catch (Exception e) {
				continue;
			}
		}
		Fxns.setAllVisible(paneComps);
	}

	protected void closeOrderly(JPane parent, ArrayList<JPane> children) {
		if (children != null) {
			for (JPane pane : children) {
				if (pane.isExpanded()) {
					closeOrderly(pane, pane.getChildren());
				}
			}
			collapse(parent);
		}
		return;
	}

	private JPopupMenu popOutMenu(JTextField field, JPane pane) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem reload = new JMenuItem("Reload");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem select = new JMenuItem("Select");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem paste = new JMenuItem("Paste");
		JMenuItem rename = new JMenuItem("Rename");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem details = new JMenuItem("Properties");

		addMenuItemListener(reload, pane);
		addMenuItemListener(open, pane);
		addMenuItemListener(select, pane);
		addMenuItemListener(copy, pane);
		addMenuItemListener(cut, pane);
		addMenuItemListener(paste, pane);
		addMenuItemListener(rename, pane);
		addMenuItemListener(delete, pane);
		addMenuItemListener(details, pane);

		menu.add(reload);
		menu.add(open);
		menu.add(select);
		menu.add(copy);
		menu.add(cut);
		menu.add(paste);
		menu.add(rename);
		menu.add(delete);
		menu.add(details);

		addPopupMenuListener(menu, field);
		field.setLayout(null);
		field.add(menu);

		return menu;
	}
	/*
	 * Beginning of listener methods Methods that add listeners to various
	 * components
	 */

	private void addPaneListeners(JButton treeNode, JTextField label, JPane pane) {
		treeNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pane.isExpanded()) {
					PopMenuActions.openNode(tree, pane);
				} else {
					closeOrderly(pane, pane.getChildren());
				}
			}
		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (!pane.isExpanded()) {
						PopMenuActions.openNode(tree, pane);
					} else {
						closeOrderly(pane, pane.getChildren());
					}
				} else if (SwingUtilities.isRightMouseButton(e)) {
					int x = label.getWidth() - 40;
					int y = label.getY();
					popOutMenu(label, pane).show(label, x, y);
				}
			}
		});
	}

	private void addMenuItemListener(JMenuItem menuItem, JPane pane) {
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = ((JMenuItem) e.getSource()).getText();

				switch (source) {
				case "Open":
					PopMenuActions.openNode(tree, pane);
					break;
				case "Reload":
					PopMenuActions.refreshNode(tree, pane);
					break;
				case "Rename":
					Tasks.rename(pane.getFile(), pane.getNodePanel());
					break;
				case "Copy":
					Tasks.copy(pane.getFile(), pane.getNodePanel());
					break;
				case "Cut":
					Tasks.cut(pane.getFile(), pane.getNodePanel());
					break;
				case "Select":
					Tasks.select(pane.getFile(), pane.getNodePanel());
					break;
				case "Paste":
					File parg = pane.getFile();
					Tasks.paste(pane.getFile());
					PopMenuActions.refreshNode(tree, Fxns.getPFolder(parg, pane, contentPane));
					break;
				case "Delete":
					File darg = pane.getFile();
					Tasks.delete(pane.getFile());
					PopMenuActions.refreshNode(tree, Fxns.getPFolder(darg, pane, contentPane));
					break;
				case "Properties":
					Tasks.properties(pane.getFile(), pane);
					break;
				default:
					System.out.println("Other Item Selected");
				}
			}
		});
	}

	private void addPopupMenuListener(JPopupMenu args, JTextField comp) {
		args.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				comp.setBackground(newColor1);
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				comp.setBackground(newColor1);
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				comp.setBackground(newColor);
			}

		});
	}

	/*
	 * External Methods Returns components for superficial altering Returns the
	 * scrollPane for adding to its parent pane
	 */

	public JLabel getTreeTitle() {
		return label;
	}

	public void setVisible(boolean args) {
		scrollPane.setVisible(args);
	}

	public JPanel getTreeTitleView() {
		return ttlPane;
	}

	public JScrollPane getContainer() {
		return scrollPane;
	}

	public void setBoundary(int x, int y, int width, int height) {
		scrollPane.setBounds(x, y, width, height);
	}
}