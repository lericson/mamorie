package se.kth.mamorie;

import java.io.IOException;
import java.util.Collection;

/**
 * A level is a set of cards to be paired up in a memory game.
 */
public abstract class Level {
	abstract public Collection<Card> getCards();
	
	public static Level level(int levelNum) throws IOException {
		if (levelNum == 1) {
			return new FileLevel("level5");
		}
		return new FileLevel("level5");
	}
	
}
