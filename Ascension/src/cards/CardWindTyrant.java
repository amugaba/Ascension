package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardWindTyrant extends Card {
	public CardWindTyrant()
	{
		super();
		name = "Wind Tyrant";
		cost = 5;
		costType = ResourceType.POWER;
		honor = 3;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		model.addRunes(3);
	}
}
