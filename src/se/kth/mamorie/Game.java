package se.kth.mamorie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Game implements Board.EventListener {
	Frame frame = null;
	Board board = null;
	Label scoreDisplay = null;
	int levelNum = 0;
	int streak = 0;
	int score = 0;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Game game = new Game();
	}

	Game() {
		frame = new Frame("Mamorie!");
		frame.setVisible(true);
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});

		frame.setLayout(new BorderLayout(Card.CARD_WIDTH / 10,
				Card.CARD_HEIGHT / 10));
		frame.setBackground(new Color(0xed, 0xed, 0xed));
		frame.setResizable(true);

		scoreDisplay = new Label();
		scoreDisplay.setAlignment(Label.CENTER);
		incrementScore(0);
		frame.add(scoreDisplay, BorderLayout.NORTH);

		setLevelNum(levelNum);
	}

	@Override
	public void pickedUp(boolean pair) {
		if (pair) {
			streak++;
			incrementScore(streak * 100);
		} else {
			streak = 0;
			incrementScore(-10);
		}
		System.err.println("Poäng: " + score);
	}

	@Override
	public void levelFinished() {
		// TODO Auto-generated method stub
		streak = 0;
		setLevelNum(levelNum + 1);
	}

	/**
	 * Increment score and update display.
	 */
	void incrementScore(int increment) {
		score += increment;
		scoreDisplay.setText("Nivå " + (levelNum + 1) + ", " + score + "p");
	}

	void setLevelNum(int levelNum) {
		if (board != null) {
			frame.remove(board);
		}

		try {
			board = Board.level(levelNum);
		} catch (Exception e) {
			board = null;
			e.printStackTrace();
		}

		if (board != null) {
			board.setEventListener(this);
			frame.add(board, BorderLayout.CENTER);
			this.levelNum = levelNum;
		} else {
			frame.add(new java.awt.Label("Spelet är slut! Du fick " + score
					+ " poäng."));
		}

		frame.pack();
		frame.repaint();
	}
}
