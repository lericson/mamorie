package se.kth.mamorie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * A level is a set of cards to be paired up in a memory game.
 */
public class Level {
	private Card[] cards;
	private String levelDir;
	private BufferedImage defaultBackImage = null;
	private Random rng = null;
	
	public Level(int levelNum) throws IOException {
		this.rng = new Random(System.nanoTime());
		this.levelDir = "res/level" + levelNum;
		this.cards = loadCards();
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
		
		int cardIndex = 0;
		List<Card> cards = Arrays.asList(new Card[2*nCards]);
		
		for (File file : files) {
			String fileName = file.getName();
			if (fileName.startsWith("card-")) {
				BufferedImage frontImage = ImageIO.read(file);
				cards.set(cardIndex++, new Card(fileName, frontImage, defaultBackImage));
				cards.set(cardIndex++, new Card(fileName, frontImage, defaultBackImage));
			}
		}
		
		assert cardIndex == cards.size();
		
		Collections.shuffle(cards, rng);
		
		return (Card[])cards.toArray();
	}
}
