package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class ViewUtil 
{
	public static final int CARD_HEIGHT = 210;
	public static final int CARD_WIDTH = 150;
	public static final int CARD_HEIGHT_MED = 168;
	public static final int CARD_WIDTH_MED = 120;
	public static final int CARD_HEIGHT_SM = 126;
	public static final int CARD_WIDTH_SM = 90;
	
	private static Map<String,ImageIcon> cardImages;
	private static Map<String,ImageIcon> cardImagesMed;
	private static Map<String,ImageIcon> cardImagesSm;
	
	public static void init()
	{
		loadCardImages();
	}
	
	private static void loadCardImages() 
	{
		cardImages = new HashMap<String,ImageIcon>();
		cardImagesMed = new HashMap<String,ImageIcon>();
		cardImagesSm = new HashMap<String,ImageIcon>();
		
		cardImages.put("Apprentice",new ImageIcon(GameView.class.getResource("/cards/images/Apprentice.jpg")));
		cardImages.put("Militia",new ImageIcon(GameView.class.getResource("/cards/images/Militia.jpg")));
		cardImages.put("Mystic",new ImageIcon(GameView.class.getResource("/cards/images/Mystic.jpg")));
		cardImages.put("Heavy Infantry",new ImageIcon(GameView.class.getResource("/cards/images/Heavy-Infantry.jpg")));
		cardImages.put("Cultist",new ImageIcon(GameView.class.getResource("/cards/images/Cultist.jpg")));
		cardImages.put("Arbiter of the Precipice",new ImageIcon(GameView.class.getResource("/cards/images/Arbiter-of-the-Precipice.jpg")));
		cardImages.put("Arha Initiate",new ImageIcon(GameView.class.getResource("/cards/images/Arha-Initiate.jpg")));
		cardImages.put("Arha Templar",new ImageIcon(GameView.class.getResource("/cards/images/Arha-Templar.jpg")));
		cardImages.put("Ascetic of the Lidless Eye",new ImageIcon(GameView.class.getResource("/cards/images/Ascetic-of-the-Lidless-Eye.jpg")));
		cardImages.put("Avatar Golem",new ImageIcon(GameView.class.getResource("/cards/images/Avatar-Golem.jpg")));
		cardImages.put("Avatar of the Fallen",new ImageIcon(GameView.class.getResource("/cards/images/Avatar-of-the-Fallen.jpg")));
		cardImages.put("Burrower Mark II",new ImageIcon(GameView.class.getResource("/cards/images/Burrower-Mark-II.jpg")));
		cardImages.put("Cetra, Weaver of Stars",new ImageIcon(GameView.class.getResource("/cards/images/Cetra-Weaver-of-Stars.jpg")));
		cardImages.put("Corrosive Widow",new ImageIcon(GameView.class.getResource("/cards/images/Corrosive-Widow.jpg")));
		cardImages.put("Demon Slayer",new ImageIcon(GameView.class.getResource("/cards/images/Demon-Slayer.jpg")));
		cardImages.put("Druids of the Stone Circle",new ImageIcon(GameView.class.getResource("/cards/images/Druids-of-the-Stone-Circle.jpg")));
		
		for(Entry<String, ImageIcon> pair : cardImages.entrySet())
		{
			cardImagesMed.put(pair.getKey(), new ImageIcon(pair.getValue().getImage().getScaledInstance(CARD_WIDTH_MED, CARD_HEIGHT_MED, Image.SCALE_SMOOTH)));
		}
		for(Entry<String, ImageIcon> pair : cardImages.entrySet())
		{
			cardImagesSm.put(pair.getKey(), new ImageIcon(pair.getValue().getImage().getScaledInstance(CARD_WIDTH_SM, CARD_HEIGHT_SM, Image.SCALE_SMOOTH)));
		}
	}

	public static ImageIcon getImage(String name) 
	{
		return cardImages.get(name);
	}
	
	public static ImageIcon getImageMedium(String name) 
	{
		return cardImagesMed.get(name);
	}
	
	public static ImageIcon getImageSmall(String name) 
	{
		return cardImagesSm.get(name);
	}
	
	public static void setStyle(JLabel label, String style)
	{
		switch(style)
		{
		case "h1":	label.setForeground(Color.BLACK);
					label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 48)); break;
		case "h3":	label.setForeground(Color.BLACK);
					label.setFont(new Font("Tahoma", Font.BOLD, 32)); break;
		}
	}
}
