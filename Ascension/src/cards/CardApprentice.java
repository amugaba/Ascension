package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardApprentice extends Card 
{
	public CardApprentice()
	{
		super();
		name = "Apprentice";
		cost = 0;
		costType = ResourceType.RUNES;
		honor = 0;
		type = CardType.HERO;
		faction = CardFaction.BASIC;
	}
	
	public void play(GameModel model)
	{
		model.addRunes(1);
	}
}
