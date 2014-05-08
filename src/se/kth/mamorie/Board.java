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
    				if (revealed[0] == null) {
        				card.reveal();	
        				revealed[0] = card;
    				}
    				else if (revealed[1] == null && card != revealed[0]) {
        				card.reveal();	
        				revealed[1] = card;	
        				checkPair();
        				//skicka iv�g par-check?
    				}
    				else if (revealed[0] != null && revealed[1] != null) {
    					revealed[0].conceal();
    					revealed[1]. conceal();
    					revealed[0] = null;
    					revealed[1] = null;
    					
    					//notis: nu ms�te vi dock klicka p� ett "kort" f�r att v�nda
    					//tillbaks korten. Funkar ej att enbart klicka p� spelplanen.
    					//Ska vi l�gga in mouseListener p� denna?
    				}
    			}
    		});
    		
    		add(card);
        }
	}
	
	public void checkPair() {
		if (revealed[0].equals(revealed[1])) {
			//PAIR!!
		}
		else {
			//Not pair..
			//null-st�lla revealed h�r?
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
