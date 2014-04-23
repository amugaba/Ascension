package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardHedronCannon extends Construct {

	public CardHedronCannon() {
		super();
		name = "Hedron Cannon";
		cost = 8;
		costType = ResourceType.RUNES;
		honor = 8;
		type = CardType.CONSTRUCT;
		faction = CardFaction.MECHANA;
	}
	
	@Override
	public void use(GameModel model)
	{
		if(active)
		{
			int numMechanaConstructs = 0;
			for(Card c : model.getActivePlayer().getConstructs())
				if(c.getFactions().contains(CardFaction.MECHANA))
					numMechanaConstructs++;
			model.addPower(numMechanaConstructs);
			active = false;
		}
	}
}
