package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardCultist extends Card 
{
	public CardCultist()
	{
		super();
		name = "Cultist";
		cost = 2;
		costType = ResourceType.POWER;
		honor = 1;
		type = CardType.MONSTER;
		faction = CardFaction.BASIC;
		location = CardLocation.COMMON_CARDS;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
	}
}
