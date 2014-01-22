package cards;

import model.CardFaction;
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
		honor = 0;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(1);
	}
}
