package se.kth.mamorie;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Game implements Board.EventListener {
	Frame frame = null;
	Board board = null;
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

		FlowLayout layout = new FlowLayout();
		layout.setHgap(Card.CARD_WIDTH/10);
		layout.setVgap(Card.CARD_HEIGHT/10);
		frame.setLayout(layout);
		frame.setBackground(new Color(0xed, 0xed, 0xed));
		frame.setResizable(true);
		
		setLevelNum(levelNum);
	}
	
	@Override
	public void pickedUp(boolean pair) {
		if (pair) {
			streak++;
			score += streak*100;
		} else {
			streak = 0;
			score -= 10;
		}
		System.err.println("Poäng: " + score);
	}
	
	@Override
	public void levelFinished() {
		// TODO Auto-generated method stub
		streak = 0;
		setLevelNum(levelNum + 1);
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
			frame.add(board);
			this.levelNum = levelNum;
		} else {
			frame.add(new java.awt.Label("Spelet är slut! Du fick " + score + " poäng."));
		}
		
		frame.pack();
		frame.repaint();
	}
}
