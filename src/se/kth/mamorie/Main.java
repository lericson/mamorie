package se.kth.mamorie;

import java.awt.Frame;

public class Main {

	public static void main(String[] args) {
		Frame frame = new Frame("Memory");
		frame.add(Board.level(1));
		frame.pack();
		frame.setVisible(true);
	}
}
