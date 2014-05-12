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
	private ArrayList<Card> cards;
	private Random rng;
	
	public PatternLevel(int levelNum) {
		rng = new Random(System.nanoTime());
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
		int n = 2+rng.nextInt(6);
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
		int r = Math.min(w, h)/2;
		RadialGradientPaint radialGradient = new RadialGradientPaint(new Point2D.Float(r, r), r, dists, colors);
		g.setPaint(radialGradient);
		g.fillRect(0, 0, w, h);
	}

}
