package me.pack.engine.players;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayImage {

	public static BufferedImage display(File ifile) throws IOException {
		BufferedImage image = ImageIO.read(ifile);
		return image;
	}
}