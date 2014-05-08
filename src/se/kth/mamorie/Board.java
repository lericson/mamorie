package se.kth.mamorie;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Board extends Panel {
	private static final long serialVersionUID = -3239991201204402152L;
	
	private Card[] revealed = new Card[2];
	
	public Board(Card[] cards, int cardsX, int cardsY) {
		GridLayout layout = new GridLayout(cardsX, cardsY);
		layout.setHgap(Card.CARD_WIDTH/10);
		layout.setVgap(Card.CARD_HEIGHT/10);
		setLayout(layout);
		
        for (Card card : cards) {
        	// TODO Pass front, back image
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
	
	/**
	 * Get a new board for level levelNum.
	 * 
	 * @param levelNum Number of the level to create a board for
	 * @return A new board for the level
	 * @throws IOException Level could not be loaded
	 */
	public static Board level(int levelNum) throws IOException {
		Level level = new Level(levelNum);
		return new Board(level.getCards(), 4, 4);
	}
}
