package se.kth.mamorie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;

/**
 * A level backed by image files on disk
 */
public class FileLevel extends Level {
	private String levelDir;
	private BufferedImage defaultBackImage = null;

	public FileLevel(String name) {
		levelDir = "res/" + name;
	}

	protected Collection<Card> loadCards() throws IOException {
		ArrayList<Card> cards = new ArrayList<Card>();

		File dir = new File(levelDir);
		File[] files = dir.listFiles();

		if (files == null) {
			throw new IOException("level not found");
		}

		try {
			defaultBackImage = ImageIO.read(new File(levelDir + "/back.jpg"));
		} catch (IOException e) {
			System.err.println("Couldn't load back image.");
			e.printStackTrace();
		}

		for (File file : files) {
			String fileName = file.getName();
			if (file.isDirectory()) {
				for (File pair : file.listFiles()) {
					BufferedImage frontImage = ImageIO.read(pair);
					cards.add(new Card(fileName, frontImage, defaultBackImage));
				}
			} else if (file.getName().startsWith("card-")) {
				BufferedImage frontImage = ImageIO.read(file);
				cards.add(new Card(fileName, frontImage, defaultBackImage));
				cards.add(new Card(fileName, frontImage, defaultBackImage));
			}
			// TODO Load board background and things like that
		}

		return cards;
	}
}
