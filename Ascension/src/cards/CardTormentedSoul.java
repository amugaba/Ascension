package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardTormentedSoul extends Card {
	public CardTormentedSoul()
	{
		super();
		name = "Tormented Soul";
		cost = 3;
		costType = ResourceType.POWER;
		honor = 1;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		model.getActivePlayer().drawCard();
	}
}
