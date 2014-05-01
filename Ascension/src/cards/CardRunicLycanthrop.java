package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardRunicLycanthrop extends Card {
	public CardRunicLycanthrop() {
		super();
		name = "Runic Lycanthrope";
		cost = 3;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.LIFEBOUND;
	}
	
	public void play(GameModel model)
	{
		model.addRunes(2);
		for(Card card : model.getPlayedCards())
		{
			if(card.getFactions().contains(CardFaction.LIFEBOUND))
			{
				model.addPower(2);
				break;
			}
		}
	}
}
