package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardSamaelsTrickster extends Card {
	public CardSamaelsTrickster()
	{
		super();
		name = "Samael's Trickster";
		cost = 3;
		costType = ResourceType.POWER;
		honor = 1;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		model.addRunes(1);
	}
}
