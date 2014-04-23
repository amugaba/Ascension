package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardAsceticoftheLidlessEye extends Card {

	public CardAsceticoftheLidlessEye() {
		super();
		name = "Ascetic of the Lidless Eye";
		cost = 5;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.HERO;
		faction = CardFaction.ENLIGHTENED;
	}

	public void play(GameModel model)
	{
		model.getActivePlayer().drawCard();
		model.getActivePlayer().drawCard();
	}
}
