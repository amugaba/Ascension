package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardDemonSlayer extends Card {

	public CardDemonSlayer() {
		super();
		name = "Demon Slayer";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.HERO;
		faction = CardFaction.VOID;
	}
	
	public void play(GameModel model)
	{
		model.addPower(3);
	}
}
