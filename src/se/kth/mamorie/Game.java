package se.kth.mamorie;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPanel;

public class Game extends JPanel implements Board.EventListener {
	private static final long serialVersionUID = 1L;

	Board board = null;
	int levelNum = 0;
	int streak = 0;
	int score = 0;
	
	public static void main(String[] args) {
		Frame frame = new Frame("Mamorie!");
		frame.add(new Game());
		frame.pack();
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
	}
	
	Game() {
		super(true);
		
		setBackground(new Color(0xed, 0xed, 0xed));
		
		FlowLayout layout = new FlowLayout();
		layout.setHgap(Card.CARD_WIDTH/10);
		layout.setVgap(Card.CARD_HEIGHT/10);
		setLayout(layout);
		
		// Triggers loading first level
		levelFinished();
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
		levelNum++;
		
		if (board != null) {
			remove(board);
		}
		
		try {
			board = Board.level(levelNum);
		} catch (Exception e) {
			board = null;
			e.printStackTrace();
		}
		
		if (board != null) {
			board.setEventListener(this);
			add(board);
		} else {
			add(new java.awt.Label("Spelet är slut! Du fick " + score + " poäng."));
		}
		
		doLayout();
		//System.err.println("preferred size: " + getLayout().minimumLayoutSize(this));
		//setPreferredSize(getLayout().preferredLayoutSize(this));
		//setMinimumSize(getLayout().minimumLayoutSize(this));
		//setSize(getPreferredSize());
		repaint();
	}
}
