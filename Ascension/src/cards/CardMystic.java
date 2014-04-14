package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardMystic extends Card 
{
	public CardMystic()
	{
		super();
		name = "Mystic";
		cost = 3;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.BASIC;
		location = CardLocation.COMMON_CARDS;
	}
	
	public void play(GameModel model)
	{
		model.addRunes(2);
	}
}
