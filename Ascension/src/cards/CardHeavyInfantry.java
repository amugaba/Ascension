package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardHeavyInfantry extends Card 
{
	public CardHeavyInfantry()
	{
		super();
		name = "Heavy Infantry";
		cost = 2;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.BASIC;
		location = CardLocation.COMMON_CARDS;
	}
	
	public void play(GameModel model)
	{
		model.addPower(2);
	}
}
