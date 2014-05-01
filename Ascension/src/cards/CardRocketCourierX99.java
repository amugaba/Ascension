package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardRocketCourierX99 extends Construct {
	public CardRocketCourierX99() {
		super();
		name = "Rocket Courier X-99";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 4;
		type = CardType.CONSTRUCT;
		faction = CardFaction.MECHANA;
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == GameAction.ACQUIRE_CONSTRUCT && active && arg instanceof Card)
		{
			Card card = (Card)arg;
			if(card.getFactions().contains(CardFaction.MECHANA))
			{
				model.moveCard(card, CardLocation.PLAYER_CONSTRUCTS);
				active = false;
			}
		}
	}
}
