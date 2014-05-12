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

	protected abstract Collection<Card> loadCards() throws IOException;

	public final Collection<Card> getCards() throws IOException {
		if (cachedCards == null) {
			cachedCards = loadCards();
		}
		ArrayList<Card> cards = new ArrayList<Card>(cachedCards);
		Collections.shuffle(cards);
		return cards;
	}

	public static Level level(int levelNum) {
		// TODO I suggest a simple list structure.
		if (levelNum-- == 0)
			return new FileLevel("meow");

		if (levelNum-- == 0)
			return new FileLevel("spelevink");

		if (levelNum-- == 0)
			return new RadialFunTimeLevel(8);

		if (levelNum-- == 0)
			return new FileLevel("teach-a-man-to-fish");

		if (levelNum-- == 0)
			return new FileLevel("shades-of-marie");

		if (levelNum-- == 0)
			return new FileLevel("parlez-vous-francais");

		if (levelNum-- == 0)
			return new FileLevel("plataldusinesiska");

		/*
		if (levelNum-- == 0)
			return new FileLevel("boss-banan");
		*/

		return null;
	}

}
