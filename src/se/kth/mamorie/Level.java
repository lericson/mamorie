package se.kth.mamorie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A level is a set of cards to be paired up in a memory game.
 */
public class Level {
	private Card[] cards;
	private String levelDir;
	private BufferedImage defaultBackImage = null;
	
	public Level(int levelNum) throws IOException {
		this.levelDir = "res/level" + levelNum;
		cards = loadCards();
	}
	
	public Card[] getCards() {
		// TODO Clone cards instead
		return cards;
	}
	
	private Card[] loadCards() throws IOException {
		File dir = new File(this.levelDir);
		File[] files = dir.listFiles();
		
		if (files == null) {
			throw new IOException("level not found");
		}
		
		int nCards = 0;
		
		for (File file : files) {
			if (file.getName().startsWith("back.")) {
				defaultBackImage = ImageIO.read(file);
			} else if (file.getName().startsWith("card-")) {
				nCards++;
			}
			// TODO Load board background and things like that
		}
		
		int i = 0;
		cards = new Card[2*nCards];
		
		for (File file : files) {
			if (file.getName().startsWith("card-")) {
				cards[i++] = loadCard(file);
				cards[i++] = loadCard(file);
			}
		}
		
		assert nCards == i;
		
		return cards;
	}
	
	private Card loadCard(File file) throws IOException {
		// TODO Implement non-default back images
		return new Card(ImageIO.read(file), defaultBackImage);
	}
}
