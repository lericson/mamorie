package se.kth.mamorie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class PatternLevel extends Level {
	private Random rng;
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
	
	void drawBack(BufferedImage image) {
		int n = 360;
		float[] dists = new float[n];
		Color[] colors = new Color[n];
		for (int i = 0; i < n; i++) {
			float y = 1.0f - ((float)i)/n;
			dists[i] = 1.0f - y;
			colors[i] = Color.getHSBColor(y, y, y);
		}
		radialFunTime(image, dists, colors);
	}
	
	void drawFront(BufferedImage image) {
		int n = 20+rng.nextInt(10);
		float[] dists = new float[n];
		Color[] colors = new Color[n];
		for (int i = 0; i < n; i++) {
			dists[i] = ((float)i)/n;
			colors[i] = Color.getHSBColor(rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
		}
		radialFunTime(image, dists, colors);
	}
	
	void radialFunTime(BufferedImage image, float[] dists, Color[] colors) {
		Graphics2D g = image.createGraphics();
		int w = image.getWidth();
		int h = image.getHeight();
		float r = (float)Math.sqrt((w*w + h*h)/4);
		Point2D center = new Point2D.Float(w/2, h/2); 
		RadialGradientPaint radialGradient = new RadialGradientPaint(center, r, dists, colors);
		g.setPaint(radialGradient);
		g.fillRect(0, 0, w, h);
	}

}
