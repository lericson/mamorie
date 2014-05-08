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
	
	public Level(int levelNum) throws IOException {
		this.levelDir = "res/level" + levelNum;
		cards = this.loadCards();
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
		
		cards = new Card[files.length];
		
		for (int i = 0; i < cards.length; i++) {
			cards[i] = loadCard(files[i]);
		}
		
		return cards;
	}
	
	private Card loadCard(File file) throws IOException {
		return new Card(loadImage(file));
	}
	
	private BufferedImage loadImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        
        int w = image.getWidth();
        int h = image.getHeight();
        
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        
        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)
        
        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, Card.CORNER_RADIUS, Card.CORNER_RADIUS));
        
        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        
        g2.dispose();
        
        return output;
	}
}
