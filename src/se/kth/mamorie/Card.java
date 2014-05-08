package se.kth.mamorie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class Card extends Component {
	private static final long serialVersionUID = 1L;
	
	final static int CARD_WIDTH = 110;
	final static int CARD_HEIGHT = 110;
	final static int CORNER_RADIUS = 25;
	
	boolean revealed = false;
	Image front = null;
	
	public Card(Image front) {
		this.front = front;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(CARD_WIDTH, CARD_HEIGHT);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setPaintMode();
		if (revealed) {
			g.drawImage(front, 0, 0, getWidth(), getHeight(), null);
		} else {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS/2, CORNER_RADIUS/2);
		}
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
