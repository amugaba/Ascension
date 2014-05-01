package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardSeeroftheForkedPath extends Card {
	public CardSeeroftheForkedPath() 
	{
		super();
		name = "Seer of the Forked Path";
		cost = 2;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.ENLIGHTENED;
	}

	public void play(GameModel model)
	{
		model.getActivePlayer().drawCard();
		model.addState(GameState.SELECT_CENTER);
		model.setRefusable(true);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_CENTER && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.isBanishable())
			{
				model.removeState(GameState.SELECT_CENTER);
				model.removeObserver(this);
				model.setRefusable(false);
				
				model.moveCard(card, CardLocation.CENTER_VOID);
			}
		}
		if(trigger == GameAction.REFUSE)
		{
			model.removeState(GameState.SELECT_CENTER);
			model.removeObserver(this);
			model.setRefusable(false);
		}
	}
}
