package cards;

import model.ActionRequest.RequestType;
import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;

public class CardCetraWeaverofStars extends Card {

	public CardCetraWeaverofStars() {
		super();
		name = "Cetra, Weaver of Stars";
		cost = 7;
		costType = ResourceType.RUNES;
		honor = 4;
		type = CardType.HERO;
		faction = CardFaction.LIFEBOUND;
	}

	@Override
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
			if(card.type == CardType.HERO)
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
		model.acquireTopDeck(card);
	}
}
