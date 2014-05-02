package cards;

import model.ActionRequest.RequestType;
import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;

public class CardMephit extends Card {

	public CardMephit() {
		super();
		name = "Mephit";
		cost = 3;
		costType = ResourceType.POWER;
		honor = 2;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
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
