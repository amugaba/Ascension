package cards;

import java.util.EnumSet;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardKortheFerromancer extends Card {

	public CardKortheFerromancer() {
		super();
		name = "Kor, the Ferromancer";
		cost = 3;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.HERO;
		faction = CardFaction.MECHANA;
	}
	
	public void play(GameModel model)
	{
		model.addPower(2);

		if(model.getActivePlayer().getConstructs().size() >= 2)
			model.getActivePlayer().drawCard();
	}
}
