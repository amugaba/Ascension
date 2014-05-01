package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardSpikeVixen extends Card {
	public CardSpikeVixen() 
	{
		super();
		name = "Spike Vixen";
		cost = 2;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.VOID;
	}

	public void play(GameModel model)
	{
		model.addPower(1);
		model.getActivePlayer().drawCard();
	}
}
