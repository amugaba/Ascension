package cards;

import java.util.EnumSet;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameException;
import model.GameModel;
import model.ResourceType;
import model.ActionRequest.RequestType;

public class CardArhaTemplar extends Card {
	public CardArhaTemplar() 
	{
		super();
		name = "Arha Templar";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 3;
		type = CardType.HERO;
		faction = CardFaction.ENLIGHTENED;
	}

	public void play(GameModel model)
	{		
		model.requestAction(RequestType.SELECT_CENTER_OR_COMMON, this, false);
	}
	
	@Override
	public boolean isActionArgumentValid(GameModel model, RequestType type, Object arg) 
	{
		if(type == RequestType.SELECT_CENTER_OR_COMMON && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.type == CardType.MONSTER && card.cost <= 4)
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
		model.acquireDefeatFree(card);
	}
}
