package cards;

import model.CardFaction;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;

public class CardSnapdragon extends Construct {
	public CardSnapdragon() {
		super();
		name = "Snapdragon";
		cost = 5;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.CONSTRUCT;
		faction = CardFaction.LIFEBOUND;
	}
	
	@Override
	public void play(GameModel model)
	{
		super.play(model);
		model.addRunes(1);
	}
	
	@Override
	public void update(GameModel model, ActionNotice trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == ActionNotice.TURN_START)
		{
			model.addRunes(1);
		}
		else if(trigger == ActionNotice.HERO_PLAYED && active && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.getFactions().contains(CardFaction.LIFEBOUND))
			{
				model.addHonor(1);
				active = false;
			}
		}
	}
}
