package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardCorrosiveWidow extends Card 
{
	public CardCorrosiveWidow()
	{
		super();
		name = "Corrosive Widow";
		cost = 4;
		costType = ResourceType.POWER;
		honor = 0;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(3);
	}
}
