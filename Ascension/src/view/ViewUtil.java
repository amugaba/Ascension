package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public final class ViewUtil 
{
	public enum CardSize
	{
		SMALL, MEDIUM, LARGE;
	}
	private static final int CARD_HEIGHT_LRG = 210;
	private static final int CARD_WIDTH_LRG = 150;
	private static final int CARD_HEIGHT_MED = 168;
	private static final int CARD_WIDTH_MED = 120;
	private static final int CARD_HEIGHT_SM = 126;
	private static final int CARD_WIDTH_SM = 90;
	
	private static Map<String,ImageIcon> cardImages;
	private static Map<String,ImageIcon> cardImagesMed;
	private static Map<String,ImageIcon> cardImagesSm;
	
	public static void init()
	{
		loadCardImages();
	}
	
	public static int cardHeight(CardSize size)
	{
		if(size == CardSize.LARGE)
			return CARD_HEIGHT_LRG;
		else if(size == CardSize.MEDIUM)
			return CARD_HEIGHT_MED;
		else
			return CARD_HEIGHT_SM;
	}
	
	public static int cardWidth(CardSize size)
	{
		if(size == CardSize.LARGE)
			return CARD_WIDTH_LRG;
		else if(size == CardSize.MEDIUM)
			return CARD_WIDTH_MED;
		else
			return CARD_WIDTH_SM;
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
		cardImages.put("Earth Tyrant",new ImageIcon(GameView.class.getResource("/cards/images/Earth-Tyrant.jpg")));
		cardImages.put("Emri, One with the Void",new ImageIcon(GameView.class.getResource("/cards/images/Emri-One-with-the-Void.jpg")));
		cardImages.put("Flytrap Witch",new ImageIcon(GameView.class.getResource("/cards/images/Flytrap-Witch.jpg")));
		cardImages.put("Hedron Cannon",new ImageIcon(GameView.class.getResource("/cards/images/Hedron-Cannon.jpg")));
		cardImages.put("Hedron Link Device",new ImageIcon(GameView.class.getResource("/cards/images/Hedron-Link-Device.jpg")));
		cardImages.put("Kor, the Ferromancer",new ImageIcon(GameView.class.getResource("/cards/images/Kor-the-Ferromancer.jpg")));
		cardImages.put("Landtalker",new ImageIcon(GameView.class.getResource("/cards/images/Landtalker.jpg")));
		cardImages.put("Lifebound Initiate",new ImageIcon(GameView.class.getResource("/cards/images/Lifebound-Initiate.jpg")));
		cardImages.put("Master Dhartha",new ImageIcon(GameView.class.getResource("/cards/images/Master-Dhartha.jpg")));
		cardImages.put("Mechana Initiate",new ImageIcon(GameView.class.getResource("/cards/images/Mechana-Initiate.jpg")));
		cardImages.put("Mephit",new ImageIcon(GameView.class.getResource("/cards/images/Mephit.jpg")));
		cardImages.put("Mistake of Creation",new ImageIcon(GameView.class.getResource("/cards/images/Mistake-of-Creation.jpg")));
		cardImages.put("Muramasa",new ImageIcon(GameView.class.getResource("/cards/images/Muramasa.jpg")));
		cardImages.put("Oziah the Peerless",new ImageIcon(GameView.class.getResource("/cards/images/Oziah-the-Peerless.jpg")));
		cardImages.put("Reactor Monk",new ImageIcon(GameView.class.getResource("/cards/images/Reactor-Monk.jpg")));
		cardImages.put("Rocket Courier X-99",new ImageIcon(GameView.class.getResource("/cards/images/Rocket-Courier-X-99.jpg")));
		cardImages.put("Runic Lycanthrope",new ImageIcon(GameView.class.getResource("/cards/images/Runic-Lycanthrope.jpg")));
		cardImages.put("Samael's Trickster",new ImageIcon(GameView.class.getResource("/cards/images/Samaels-Trickster.jpg")));
		cardImages.put("Sea Tyrant",new ImageIcon(GameView.class.getResource("/cards/images/Sea-Tyrant.jpg")));
		cardImages.put("Seer of the Forked Path",new ImageIcon(GameView.class.getResource("/cards/images/Seer-of-the-Forked-Path.jpg")));
		cardImages.put("Shade of the Black Watch",new ImageIcon(GameView.class.getResource("/cards/images/Shade-of-the-Black-Watch.jpg")));
		cardImages.put("Shadow Star",new ImageIcon(GameView.class.getResource("/cards/images/Shadow-Star.jpg")));
		cardImages.put("Snapdragon",new ImageIcon(GameView.class.getResource("/cards/images/Snapdragon.jpg")));
		cardImages.put("Spike Vixen",new ImageIcon(GameView.class.getResource("/cards/images/Spike-Vixen.jpg")));
		cardImages.put("Tablet of Time's Dawn",new ImageIcon(GameView.class.getResource("/cards/images/Tablet-of-Times-Dawn.jpg")));
		cardImages.put("Temple Librarian",new ImageIcon(GameView.class.getResource("/cards/images/Temple-Librarian.jpg")));
		cardImages.put("The All-Seeing Eye",new ImageIcon(GameView.class.getResource("/cards/images/The-All-Seeing-Eye.jpg")));
		cardImages.put("The Grand Design",new ImageIcon(GameView.class.getResource("/cards/images/The-Grand-Design.jpg")));
		cardImages.put("Tormented Soul",new ImageIcon(GameView.class.getResource("/cards/images/Tormented-Soul.jpg")));
		cardImages.put("Twofold Askara",new ImageIcon(GameView.class.getResource("/cards/images/Twofold-Askara.jpg")));
		cardImages.put("Void Initiate",new ImageIcon(GameView.class.getResource("/cards/images/Void-Initiate.jpg")));
		cardImages.put("Voidthirster",new ImageIcon(GameView.class.getResource("/cards/images/Voidthirster.jpg")));
		cardImages.put("Watchmaker's Altar",new ImageIcon(GameView.class.getResource("/cards/images/Watchmakers-Altar.jpg")));
		cardImages.put("Wind Tyrant",new ImageIcon(GameView.class.getResource("/cards/images/Wind-Tyrant.jpg")));
		cardImages.put("Wolf Shaman",new ImageIcon(GameView.class.getResource("/cards/images/Wolf-Shaman.jpg")));
		cardImages.put("Xeron, Duke of Lies",new ImageIcon(GameView.class.getResource("/cards/images/Xeron-Duke-of-Lies.jpg")));
		cardImages.put("Yggdrasil Staff",new ImageIcon(GameView.class.getResource("/cards/images/Yggdrasil-Staff.jpg")));		
		
		for(Entry<String, ImageIcon> pair : cardImages.entrySet())
		{
			cardImagesMed.put(pair.getKey(), new ImageIcon(pair.getValue().getImage().getScaledInstance(CARD_WIDTH_MED, CARD_HEIGHT_MED, Image.SCALE_SMOOTH)));
		}
		for(Entry<String, ImageIcon> pair : cardImages.entrySet())
		{
			cardImagesSm.put(pair.getKey(), new ImageIcon(pair.getValue().getImage().getScaledInstance(CARD_WIDTH_SM, CARD_HEIGHT_SM, Image.SCALE_SMOOTH)));
		}
	}

	public static ImageIcon getImage(String name, CardSize size) 
	{
		if(size == CardSize.LARGE)
			return cardImages.get(name);
		else if(size == CardSize.MEDIUM)
			return cardImagesMed.get(name);
		else
			return cardImagesSm.get(name);
	}
	
	public enum Style
	{
		H1, H3
	}
	
	public static void setStyle(JComponent label, Style style)
	{
		if(style == Style.H1)
		{
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 48));
		}
		else if(style == Style.H3)
		{
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Tahoma", Font.BOLD, 32));
		}
	}
}
