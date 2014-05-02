package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;
import model.ActionRequest.RequestType;

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
		model.requestAction(RequestType.SELECT_CENTER, this, true);
	}
	
	@Override
	public boolean isActionArgumentValid(GameModel model, RequestType type, Object arg) 
	{
		if(type == RequestType.SELECT_CENTER && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.isBanishable())
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;			
		model.moveCard(card, CardLocation.CENTER_VOID);
	}
}
