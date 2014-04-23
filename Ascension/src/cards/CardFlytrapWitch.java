package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardFlytrapWitch extends Card {

	public CardFlytrapWitch() {
		super();
		name = "Flytrap Witch";
		cost = 5;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.HERO;
		faction = CardFaction.LIFEBOUND;
	}

	public void play(GameModel model)
	{
		model.getActivePlayer().drawCard();
		model.addHonor(2);
	}
}
