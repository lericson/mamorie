package se.kth.mamorie;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
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
	private File defaultBackImageFile = null;
	
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
		
		for (File file : dir.listFiles()) {
			if (file.getName().startsWith("back.")) {
				defaultBackImageFile = file;
			} else if (file.getName().startsWith("card-")) {
				nCards++;
			}
			// TODO Load board background and things like that
		}
		
		int i = 0;
		cards = new Card[nCards];
		
		for (File file : dir.listFiles()) {
			if (file.getName().startsWith("card-")) {
				cards[i++] = loadCard(file);
			}
		}
		
		assert nCards == i;
		
		return cards;
	}
	
	private Card loadCard(File file) throws IOException {
		// TODO Implement non-default back images
		return new Card(file, defaultBackImageFile);
	}
}
