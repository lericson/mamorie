package se.kth.mamorie;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public abstract class PatternLevel extends Level {
	protected Random rng;
	private int numPairs; 
	
	public PatternLevel(int numPairs) {
		this.rng = new Random(System.nanoTime());
		this.numPairs = numPairs;
	}
	
	@Override
	public Collection<Card> loadCards() {
		ArrayList<Card> cards = new ArrayList<Card>(2*numPairs);
		
		BufferedImage back = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		drawBack(back);
		
		while (cards.size()/2 < numPairs) {
			String pairId = String.valueOf(cards.size());
			BufferedImage front = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
			drawFront(front);
			cards.add(new Card(pairId, front, back));
			cards.add(new Card(pairId, front, back));
		}
		
		return cards;
	}
	
	/**
	 * Draw the back image of all cards for this level. 
	 */
	abstract void drawBack(BufferedImage image);
	
	/**
	 * Draw the front image of a pair of cards for this level.
	 * @param image
	 */
	abstract void drawFront(BufferedImage image);
}
