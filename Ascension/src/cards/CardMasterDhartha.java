package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardMasterDhartha extends Card {

	public CardMasterDhartha() {
		super();
		name = "Master Dhartha";
		cost = 7;
		costType = ResourceType.RUNES;
		honor = 3;
		type = CardType.HERO;
		faction = CardFaction.ENLIGHTENED;
	}

	public void play(GameModel model)
	{
		model.getActivePlayer().drawCard();
		model.getActivePlayer().drawCard();
		model.getActivePlayer().drawCard();
	}
}
