package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;
import model.ActionRequest.RequestType;

public class CardMistakeofCreation extends Card {
	
	public CardMistakeofCreation() {
		super();
		name = "Mistake of Creation";
		cost = 4;
		costType = ResourceType.POWER;
		honor = 4;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		model.requestAction(RequestType.SELECT_CENTER, this, true);
		model.requestAction(RequestType.SELECT_DISCARD, this, true);
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
		if(type == RequestType.SELECT_DISCARD && arg instanceof Card)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;		
		if(type == RequestType.SELECT_CENTER)
		{
			model.moveCard(card, CardLocation.CENTER_VOID);		
		}
		if(type == RequestType.SELECT_DISCARD)
		{
			model.moveCard(card, CardLocation.CENTER_VOID);
		}
	}
}
