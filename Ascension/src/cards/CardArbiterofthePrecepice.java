package cards;

import model.ActionRequest;
import model.ActionRequest.RequestType;
import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;

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
		
		model.requestAction(RequestType.SELECT_HAND, this, false);
		//model.addState(RequestType.SELECT_HAND);
		//model.addObserver(this);
	}
	
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;
		model.moveCard(card, CardLocation.CENTER_VOID);
	}
	
	/*@Override
	public void update(GameModel model, ActionNotice trigger, Object arg) 
	{	
		if(trigger == ActionNotice.HAND_SELECTED && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.removeState(RequestType.SELECT_HAND);
			model.removeObserver(this);
			
			model.moveCard(card, CardLocation.CENTER_VOID);
		}
	}*/

}
