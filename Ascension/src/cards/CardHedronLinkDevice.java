package cards;

import model.CardFaction;
import model.CardType;
import model.ResourceType;

public class CardHedronLinkDevice extends Construct {

	public CardHedronLinkDevice() {
		super();
		name = "Hedron Link Device";
		cost = 7;
		costType = ResourceType.RUNES;
		honor = 7;
		type = CardType.CONSTRUCT;
		faction = CardFaction.MECHANA;
	}
}
