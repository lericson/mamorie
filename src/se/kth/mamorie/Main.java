package se.kth.mamorie;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {

	public static void main(String[] args) {
		Frame frame = new Frame("Mamorie");
		frame.setBackground(new Color(0xed, 0xed, 0xed));

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

		try {
			frame.add(Board.level(1));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		frame.pack();
		frame.setVisible(true);
	}
}
