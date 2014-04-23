package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;
import model.GameState;

public class CardArbiterofthePrecepice extends Card {

	public CardArbiterofthePrecepice() 
	{
		super();
		name = "Arbiter of the Precipice";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.VOID;
	}

	public void play(GameModel model)
	{
		model.getActivePlayer().drawCard();
		model.getActivePlayer().drawCard();
		
		model.addState(GameState.SELECT_CARD_HAND);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_HAND && arg instanceof Card)
		{
			Card card = (Card) arg;
			//model.banishPlayerCard(card);
			model.moveCard(card, CardLocation.CENTER_VOID);
			model.removeState(GameState.SELECT_CARD_HAND);
			model.removeObserver(this);
		}
	}

}
