package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardTheAllSeeingEye extends Construct {
	public CardTheAllSeeingEye() {
		super();
		name = "The All-Seeing Eye";
		cost = 6;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.CONSTRUCT;
		faction = CardFaction.ENLIGHTENED;
	}
	
	@Override
	public void use(GameModel model)
	{
		if(active)
		{
			model.getActivePlayer().drawCard();
			active = false;
		}
	}
}
