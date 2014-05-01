package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardWolfShaman extends Card {
	public CardWolfShaman() {
		super();
		name = "Wolf Shaman";
		cost = 3;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.LIFEBOUND;
	}

	public void play(GameModel model)
	{
		model.getActivePlayer().drawCard();
		model.addRunes(1);
	}
}
