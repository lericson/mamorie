package se.kth.mamorie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A level is a set of cards to be paired up in a memory game.
 */
public abstract class Level {
	private Collection<Card> cachedCards = null;
	abstract protected Collection<Card> loadCards() throws IOException;
	
	final public Collection<Card> getCards() throws IOException {
		if (cachedCards == null) {
			cachedCards = loadCards();
		}
		ArrayList<Card> cards = new ArrayList<Card>(cachedCards);
		Collections.shuffle(cards);
		return cards;
	}
	
	public static Level level(int levelNum) {
		switch (levelNum) {
		case 1: return new FileLevel("level1");
		case 2: return new FileLevel("level2");
		case 3: return new FileLevel("level3");
		case 4: return new FileLevel("level4");
		case 5: return new FileLevel("level5");
		case 6: return new FileLevel("level6");
		case 7: return new FileLevel("level7");
		}
		return new FileLevel("level7");
	}
	
}
