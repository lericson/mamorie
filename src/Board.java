package se.kth.mamorie;

import java.awt.Component;
import java.awt.Dimension;

public class Board extends Component {
	private static final long serialVersionUID = -3239991201204402152L;
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
	
	public static Board level(int level) {
		return new Board();
	}
}
