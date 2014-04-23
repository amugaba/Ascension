package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardLifeboundInitiate extends Card {

	public CardLifeboundInitiate() {
		super();
		name = "Lifebound Initiate";
		cost = 1;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.LIFEBOUND;
	}
	
	public void play(GameModel model)
	{
		model.addRunes(1);
		model.addHonor(1);
	}
}
