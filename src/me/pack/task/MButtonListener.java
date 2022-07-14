package me.pack.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import me.pack.lookandfeel.active.FullImageThumbs;

public class MButtonListener {

	public static void moreImagesWaiter(JButton images) {
		images.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				FullImageThumbs.start();
			}
		});
	}

	public static void moreAudiosWaiter(JButton audios) {
		audios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {

			}
		});
	}

	public static void moreVideosWaiter(JButton videos) {
		videos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {

			}
		});
	}

}
