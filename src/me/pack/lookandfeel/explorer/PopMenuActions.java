package me.pack.lookandfeel.explorer;

import me.pack.task.ImageTasks;
import me.pack.task.SoundTasks;
import me.pack.task.VideoTasks;

public class PopMenuActions {

	protected static void refreshNode(MFileTree tree, JPane pane) {
		if (pane.isExpanded()) {
			tree.closeOrderly(pane, pane.getChildren());
			tree.expand(pane);
		}
	}

	protected static void openNode(MFileTree tree, JPane pane) {
		if (pane.isExpandableFolder()) {
			tree.expand(pane);
		} else if (pane.isRightFile()) {
			int type = pane.getMediaFileType();
			switch (type) {
			case 0:
				break;
			case 1:
				VideoTasks.open(pane.getFile());
				break;
			case 2:
				SoundTasks.open(pane.getFile());
				break;
			case 3:
				ImageTasks.open(pane.getFile());
				break;
			}
		}
	}

}
