package se.kth.mamorie;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Board extends Panel {
	private static final long serialVersionUID = -3239991201204402152L;
	int cardsX;
	int cardsY;
	
	Card[] revealed = new Card[2];
	
	public Board(int cardsX, int cardsY) throws IOException {
		this.cardsX = cardsX;
		this.cardsY = cardsY;
		
		GridLayout layout = new GridLayout(cardsX, cardsY);
		layout.setHgap(Card.CARD_WIDTH/10);
		layout.setVgap(Card.CARD_HEIGHT/10);
		setLayout(layout);
		
		// TODO Put in own static method
        BufferedImage image = ImageIO.read(new File("res/test.jpg"));
        
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
		
        for (int i = 0; i < cardsX*cardsY; i++) {
        	// TODO Pass front, back image
        	Card card = new Card(output);
    		card.addMouseListener(new MouseListener() {

    			@Override
    			public void mouseReleased(MouseEvent e) {
    				//int height = c.getHeight();
    			}
    			
    			@Override
    			public void mousePressed(MouseEvent e) {
    				// TODO Auto-generated method stub
    				
    			}
    			
    			@Override
    			public void mouseExited(MouseEvent e) {
    				// TODO Auto-generated method stub
    				
    			}
    			
    			@Override
    			public void mouseEntered(MouseEvent e) {
    				// TODO Auto-generated method stub
    				
    			}
    			
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				Card card = (Card)e.getSource();
    				card.reveal();
    			}
    		});
    		
    		add(card);
        }
	}
	
	public static Board level(int level) throws IOException {
		assert level == 1;
		return new Board(4, 4);
	}
}
