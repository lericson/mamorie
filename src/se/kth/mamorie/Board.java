package se.kth.mamorie;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

import javax.swing.Timer;

public class Board extends Panel {
	private static final long serialVersionUID = 1L;

	static final int COLLECT_TIMEOUT = 1000;

	private Card revealed1 = null;
	private Card revealed2 = null;
	private int numCards = 0, numPaired = 0;
	private Timer cardCollectTimer = null;

	interface EventListener {
		public void pickedUp(boolean pair);
		public void levelFinished();
	}

	private EventListener listener = null;

	/**
	 * Get a new board for level levelNum.
	 * 
	 * @param levelNum
	 *            Number of the level to create a board for
	 * @return A new board for the level
	 * @throws IOException
	 *             Level could not be loaded
	 */
	public static Board level(int levelNum) throws IOException {
		Level level = Level.level(levelNum);
		if (level == null) {
			return null;
		}
		return new Board(level.getCards(), 4, 4);
	}

	public Board(Collection<Card> cards, int cardsX, int cardsY) {
		final Board self = this;

		numCards = cards.size();

		GridLayout layout = new GridLayout(cardsX, cardsY);
		layout.setHgap(Card.CARD_WIDTH / 10);
		layout.setVgap(Card.CARD_HEIGHT / 10);
		setLayout(layout);

		cardCollectTimer = new Timer(COLLECT_TIMEOUT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				self.resetBoard();
			}
		});

		cardCollectTimer.setRepeats(true);
		cardCollectTimer.start();

		for (Card card : cards) {
			add(card);
			card.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) { }
				public void mousePressed(MouseEvent e) { } 
				public void mouseExited(MouseEvent e) { }
				public void mouseEntered(MouseEvent e) { }

				@Override
				public void mouseClicked(MouseEvent e) {
					Card card = (Card) e.getSource();

					self.resetBoard();

					if (revealed1 == null && revealed2 == null) {
						card.reveal();
						revealed1 = card;
					} else if (revealed2 == null && card != revealed1) {
						card.reveal();
						revealed2 = card;
					}
				}
			});
		}
	}

	/**
	 * Reset the board if two cards are picked up.
	 */
	void resetBoard() {
		cardCollectTimer.restart();

		if (revealed1 == null || revealed2 == null) {
			return;
		}

		boolean isPair = revealed1.equals(revealed2);

		listener.pickedUp(isPair);

		if (isPair) {
			revealed1.setVisible(false);
			revealed2.setVisible(false);
			numPaired += 2;
			repaint();
			// TODO Poäng
			// TODO Vinna
		} else {
			// Nullställning
			revealed1.conceal();
			revealed2.conceal();
		}

		revealed1 = null;
		revealed2 = null;

		if (numPaired == numCards) {
			listener.levelFinished();
		}
	}

	public void setEventListener(EventListener listener) {
		this.listener = listener;
	}

}
