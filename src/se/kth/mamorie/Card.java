package se.kth.mamorie;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Card extends Component {
	private static final long serialVersionUID = 1L;
	
	final static int CARD_WIDTH = 110;
	final static int CARD_HEIGHT = 110;
	final static int CORNER_RADIUS = 25;
	
	private boolean revealed = false;
	private BufferedImage front = null;
	private BufferedImage back = null;
	
	/**
	 * Create a new card with front and back images
	 * 
	 * @param frontImageFile Front image file
	 * @param backImageFile Back image file
	 */
	public Card(BufferedImage front, BufferedImage back) throws IOException {
		this.front = front;
		this.back = back;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(CARD_WIDTH, CARD_HEIGHT);
	}
	
	@Override
	public void paint(Graphics g) {
		//g.drawImage(revealed ? front : back, 0, 0, getWidth(), getHeight(), null);
		
		BufferedImage image = revealed ? front : back;
		
        int w = getWidth();
        int h = getHeight();
        
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
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        
        g2.dispose();
        
        g.drawImage(output, 0, 0, null);
	}
	
	public void reveal() {
		revealed = true;
		repaint();
	}
	
	public void conceal() {
		revealed = false;
		repaint();
	}
}
