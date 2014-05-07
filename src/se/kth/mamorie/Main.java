package se.kth.mamorie;

import java.awt.FlowLayout;
import java.awt.Frame;

public class Main {

	public static void main(String[] args) {
		Frame frame = new Frame("Memory");
		
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
