package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardVoidInitiate extends Card {
	public CardVoidInitiate() 
	{
		super();
		name = "Void Initiate";
		cost = 1;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.VOID;
	}

	public void play(GameModel model)
	{
		model.addRunes(1);
		
		model.addState(GameState.SELECT_HAND_OR_DISCARD);
		model.setRefusable(true);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if((trigger == GameAction.SELECT_HAND || trigger == GameAction.SELECT_DISCARD) && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.removeState(GameState.SELECT_HAND_OR_DISCARD);
			model.removeObserver(this);
			model.setRefusable(false);
			
			model.moveCard(card, CardLocation.CENTER_VOID);
		}
		if(trigger == GameAction.REFUSE)
		{
			model.removeState(GameState.SELECT_HAND_OR_DISCARD);
			model.removeObserver(this);
			model.setRefusable(false);
		}
	}
}
