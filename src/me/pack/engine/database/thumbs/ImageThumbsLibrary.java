package me.pack.engine.database.thumbs;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageThumbsLibrary {

	private static String separator = File.separator;

	private static File archive = new File("thumbs" + separator + "images" + separator + ".metadata");

	private static Dimension d;

	public static File browseThumbnails(File ifile, Dimension size) throws Exception {
		d = size;
		if (archive.exists()) {
			deserializeMeta(ifile);
		} else {
			createMetaFile(ifile);
		}

		return archive;
	}

	public static File getArchiveFile() {
		return archive;
	}

	@SuppressWarnings("unchecked")
	private static void deserializeMeta(File ifile) throws Exception {

		ArrayList<Thumbnail> metaMap;

		// Deserialize
		FileInputStream fis = new FileInputStream(archive);
		ObjectInputStream ois = new ObjectInputStream(fis);
		metaMap = (ArrayList<Thumbnail>) ois.readObject();
		ois.close();

		// Check For fileThumb presence

		Thumbnail test = new Thumbnail(ifile.getAbsolutePath());
		if (metaMap.contains(test)) {
			int index = metaMap.indexOf(test);
			Thumbnail real = metaMap.get(index);
			metaMap.remove(real);
			metaMap.add(0, real);
		} else {

			// Obtain Image from file
			String path = ifile.getAbsolutePath();
			BufferedImage temp = ImageIO.read(ifile);

			// Create thumbnail from obtained image
			Image thumbnail = temp.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
			Thumbnail tn = new Thumbnail(path);
			tn.addThumbImage(thumbnail, d);

			// Add Object to arraylist
			metaMap.add(0, tn);
		}

		// Serialize
		FileOutputStream fos = new FileOutputStream(archive);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(metaMap);
		oos.close();
	}

	private static void createMetaFile(File ifile) throws IOException {
		ArrayList<Thumbnail> metaMap = new ArrayList<>();

		// Obtain Image from file
		String path = ifile.getAbsolutePath();
		BufferedImage temp = ImageIO.read(ifile);

		// Create thumbnail from obtained image
		Image thumbnail = temp.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
		Thumbnail tn = new Thumbnail(path);
		tn.addThumbImage(thumbnail, d);

		metaMap.add(0, tn);

		// Serialize
		FileOutputStream fos = new FileOutputStream(archive);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(metaMap);
		oos.close();

	}

	@SuppressWarnings("unchecked")
	public static void updateArchive() throws Exception {
		FileInputStream fis = new FileInputStream(archive);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Thumbnail> metaMap = (ArrayList<Thumbnail>) ois.readObject();
		ois.close();

		ArrayList<Thumbnail> metadata = new ArrayList<Thumbnail>();
		for (Thumbnail tn : metaMap) {
			if (new File(tn.getPath()).exists()) {
				metadata.add(metadata.size(), tn);
			}
		}

		FileOutputStream fos = new FileOutputStream(archive);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(metadata);
		oos.close();
	}
}