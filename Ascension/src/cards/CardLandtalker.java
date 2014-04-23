package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardLandtalker extends Card {

	public CardLandtalker() {
		super();
		name = "Landtalker";
		cost = 6;
		costType = ResourceType.RUNES;
		honor = 3;
		type = CardType.HERO;
		faction = CardFaction.LIFEBOUND;
	}
	
	public void play(GameModel model)
	{
		model.addRunes(3);
	}
}
