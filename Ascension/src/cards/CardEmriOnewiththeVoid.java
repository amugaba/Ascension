package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardEmriOnewiththeVoid extends Card {

	public CardEmriOnewiththeVoid() {
		super();
		name = "Emri, One with the Void";
		cost = 6;
		costType = ResourceType.RUNES;
		honor = 3;
		type = CardType.HERO;
		faction = CardFaction.VOID;
	}

	public void play(GameModel model)
	{
		model.addPower(4);
	}
}
