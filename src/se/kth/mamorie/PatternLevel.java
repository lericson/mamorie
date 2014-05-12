package se.kth.mamorie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PatternLevel extends Level {
	ArrayList<Card> cards;
	
	public PatternLevel(int levelNum) {
		cards = new ArrayList<Card>(16);
		
		BufferedImage back = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		drawBack(back);
		
		while (cards.size() < 16) {
			String pairId = String.valueOf(cards.size());
			BufferedImage front = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
			drawFront(front);
			cards.add(new Card(pairId, front, back));
			cards.add(new Card(pairId, front, back));
		}
	}

	@Override
	public Collection<Card> getCards() {
		return new ArrayList<Card>(cards);
	}
	
	void drawBack(BufferedImage image) {
		Graphics2D g = image.createGraphics();
		float[] dists = {0.0f, 0.2f, 1.0f};
		Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
		RadialGradientPaint radialGradient = new RadialGradientPaint(new Point2D.Float(250.0f, 250.0f), 250.0f, dists, colors);
		g.setPaint(radialGradient);
		g.fillRect(0, 0, 500, 500);
		
	}
	
	void drawFront(BufferedImage image) {
		Graphics2D g = image.createGraphics();
	}

}
