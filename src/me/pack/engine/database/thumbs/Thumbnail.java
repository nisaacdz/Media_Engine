package me.pack.engine.database.thumbs;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Thumbnail implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] byteStream;
	private String path;

	public Thumbnail(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thumbnail other = (Thumbnail) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	public void addThumbImage(Image image, Dimension d) {
		BufferedImage bfi = new BufferedImage(d.width, d.height, Image.SCALE_SMOOTH);
		bfi.getGraphics().drawImage(image, 0, 0, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bfi, "jpg", baos);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		this.byteStream = baos.toByteArray();
	}

	public String getPath() {
		return this.path;
	}

	public Image getThumbImage() {
		BufferedImage image;
		try {
			image = ImageIO.read(new ByteArrayInputStream(byteStream));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return image;
	}
}