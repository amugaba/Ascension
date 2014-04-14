package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardArhaInitiate extends Card {
	public CardArhaInitiate() 
	{
		super();
		name = "Arha Initiate";
		cost = 1;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.ENLIGHTENED;
	}

	public void play(GameModel model)
	{
		model.getActivePlayer().drawCard();
	}
}
