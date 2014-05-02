package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
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
	public void update(GameModel model, ActionNotice trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == ActionNotice.CONSTRUCT_ACQUIRED && active && arg instanceof Card)
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
