package se.kth.mamorie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * A level backed by image files on disk
 */
public class FileLevel extends Level {
	private Collection<Card> cards;
	private String levelDir;
	private BufferedImage defaultBackImage = null;
	
	public FileLevel(String name) throws IOException {
		levelDir = "res/" + name;
		cards = new ArrayList<Card>();
		loadCards();
	}
	
	@Override
	public Collection<Card> getCards() {
		return new ArrayList<Card>(cards);
	}
	
	private void loadCards() throws IOException {
		File dir = new File(levelDir);
		File[] files = dir.listFiles();
		
		if (files == null) {
			throw new IOException("level not found");
		}
		
		try {
			defaultBackImage = ImageIO.read(new File(levelDir + "/back.jpg"));
		} catch (IOException e) {
			System.err.println("Couldn't load back image.");
			e.printStackTrace();
		}
			
		for (File file : files) {
			if (file.isDirectory()) {
				loadPair(file);
			} else if (file.getName().startsWith("card-")) {
				loadCard(file);				
			}
			// TODO Load board background and things like that
		}
	}
	
	public void loadPair(File pairFile) throws IOException {
		File[] pair = pairFile.listFiles();
		String fileName = pair[0].getName();
		if (pair.length > 1) {
			BufferedImage frontImage1 = ImageIO.read(pair[0]);
			BufferedImage frontImage2 = ImageIO.read(pair[1]);
			cards.add(new Card(fileName, frontImage1, defaultBackImage));
			cards.add(new Card(fileName, frontImage2, defaultBackImage));	
		}	
	}
	public void loadCard(File cardFile) throws IOException {	
		String fileName = cardFile.getName();
		BufferedImage frontImage = ImageIO.read(cardFile);
		cards.add(new Card(fileName, frontImage, defaultBackImage));
		cards.add(new Card(fileName, frontImage, defaultBackImage));			
		}
}
