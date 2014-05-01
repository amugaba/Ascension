package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardReactorMonk extends Card {
	public CardReactorMonk() {
		super();
		name = "Reactor Monk";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.HERO;
		faction = CardFaction.MECHANA;
	}
	
	public void play(GameModel model)
	{
		model.addRunes(2);
		model.addConstructRunes(1);
	}
}
