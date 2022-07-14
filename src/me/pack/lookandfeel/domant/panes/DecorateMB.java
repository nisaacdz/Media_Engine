package me.pack.lookandfeel.domant.panes;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DecorateMB {

	private static JMenu video, audio, image;
	private static JMenu view, tools, playback;
	private static JMenu subtitle, settings;

	public static void perform(JMenuBar bar) {
		createMenus();
		decorateMenus(getMenus());
		addMenus(bar);
		subOperations();
	}

	private static void createMenus() {
		video = new JMenu("Video");
		audio = new JMenu("Audio");
		image = new JMenu("Image");
		view = new JMenu("View");
		tools = new JMenu("Tools");
		playback = new JMenu("Playback");
		subtitle = new JMenu("Subtitle");
		settings = new JMenu("Settings");
	}

	private static void decorateMenus(JMenu[] arg) {
		for (JMenu menu : arg) {
			menu.setBackground(Color.BLACK);
			menu.setForeground(Color.YELLOW);
		}
	}

	private static void addMenus(JMenuBar bar) {
		bar.add(video);
		bar.add(audio);
		bar.add(image);
		bar.add(view);
		bar.add(tools);
		bar.add(playback);
		bar.add(subtitle);
		bar.add(settings);
	}

	private static void subOperations() {
		video(video);
		audio(audio);
		image(image);
		view(view);
		tools(tools);
		playback(playback);
		subtitle(subtitle);
		settings(settings);
	}

	private static JMenu[] getMenus() {
		JMenu[] i = { video, audio, image, view, tools, playback, subtitle, settings };
		return i;
	}

	private static void video(JMenu video) {
		JMenuItem scanV = new JMenuItem("Scan For Videos");
		JMenuItem zoom = new JMenuItem("Zoom");
		JMenuItem videoT = new JMenuItem("Video Track");
		JMenuItem aspectR = new JMenuItem("Aspect Ratio");
		JMenuItem mute = new JMenuItem("Mute");
		JMenuItem snap = new JMenuItem("Snap");

		video.add(scanV);
		video.add(zoom);
		video.add(videoT);
		video.add(aspectR);
		video.add(mute);
		video.add(snap);
	}

	private static void audio(JMenu audio) {
		JMenuItem scanA = new JMenuItem("Scan For Audios");
		JMenuItem audioT = new JMenuItem("Audio Track");
		JMenuItem increaseV = new JMenuItem("Increase Volune");
		JMenuItem decreaseV = new JMenuItem("Decrease Volume");
		JMenuItem mute = new JMenuItem("Mute");
		JMenuItem device = new JMenuItem("Select Audio Device");

		audio.add(scanA);
		audio.add(audioT);
		audio.add(increaseV);
		audio.add(decreaseV);
		audio.add(mute);
		audio.add(device);
	}

	private static void image(JMenu image) {
		JMenuItem scanA = new JMenuItem("Scan For Images");
		JMenuItem share = new JMenuItem("Share");
		JMenuItem next = new JMenuItem("Next");
		JMenuItem previous = new JMenuItem("Previous");

		image.add(scanA);
		image.add(share);
		image.add(next);
		image.add(previous);
	}

	private static void view(JMenu view) {
		JMenuItem fullScreen = new JMenuItem("Fullscreen Interface");
		JMenuItem minimal = new JMenuItem("Minimal Interface");
		JMenuItem bestFit = new JMenuItem("Best Fit");
		JMenuItem taskbar = new JMenuItem("Taskbar");
		JMenuItem locked = new JMenuItem("Locked");

		view.add(fullScreen);
		view.add(minimal);
		view.add(bestFit);
		view.add(taskbar);
		view.add(locked);

	}

	private static void tools(JMenu tools) {
		JMenuItem resolution = new JMenuItem("Resolution");
		JMenuItem codecI = new JMenuItem("Codec Information");
		JMenuItem effects = new JMenuItem("Effects and Filters");
		JMenuItem trackS = new JMenuItem("Track Synchronization");
		JMenuItem customizeI = new JMenuItem("Customize Interface");

		tools.add(resolution);
		tools.add(codecI);
		tools.add(effects);
		tools.add(trackS);
		tools.add(customizeI);
	}

	private static void playback(JMenu playback) {
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem play = new JMenuItem("Play");
		JMenuItem stop = new JMenuItem("Stop");
		JMenuItem next = new JMenuItem("Next");
		JMenuItem previous = new JMenuItem("Previous");
		JMenuItem forward = new JMenuItem("Forward");
		JMenuItem backward = new JMenuItem("Backward");
		JMenuItem record = new JMenuItem("Record");
		JMenuItem jump = new JMenuItem("Jump To Time");
		JMenuItem speed = new JMenuItem("Playback Speed");

		playback.add(pause);
		playback.add(play);
		playback.add(stop);
		playback.add(next);
		playback.add(previous);
		playback.add(forward);
		playback.add(backward);
		playback.add(record);
		playback.add(jump);
		playback.add(speed);
	}

	private static void subtitle(JMenu subtitle) {
		JMenuItem addSub = new JMenuItem("Add Subtitle File");
		JMenuItem dowSub = new JMenuItem("Download Subtitle");
		JMenuItem choSub = new JMenuItem("Choose Subtitle Track");

		subtitle.add(addSub);
		subtitle.add(dowSub);
		subtitle.add(choSub);
	}

	private static void settings(JMenu settings) {

		JMenuItem update = new JMenuItem("Update");
		JMenuItem programG = new JMenuItem("Program Guide");
		JMenuItem configuration = new JMenuItem("Configuration");
		JMenuItem preferences = new JMenuItem("Preferences");
		JMenuItem pluginsE = new JMenuItem("Plugins And Extensions");
		JMenuItem advanced = new JMenuItem("Advanced");

		settings.add(configuration);
		settings.add(programG);
		settings.add(update);
		settings.add(preferences);
		settings.add(pluginsE);
		settings.add(advanced);
	}
}
