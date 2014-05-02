package cards;

import model.CardFaction;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;
import model.ActionRequest.RequestType;

public class CardMechanaInitiate extends Card {

	public CardMechanaInitiate(){
		super();
		name = "Mechana Initiate";
		cost = 1;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.MECHANA;
	}

	public void play(GameModel model)
	{
		model.requestAction(RequestType.SELECT_OPTION, this, false);
	}
	
	@Override
	public boolean isActionArgumentValid(GameModel model, RequestType type, Object arg) 
	{
		if(type == RequestType.SELECT_OPTION && arg instanceof Integer)
		{
			Integer option = (Integer) arg;
			if(option == 0 || option == 1)
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
		Integer option = (Integer) arg;		
		if(option == 0)
			model.addRunes(1);
		else if(option == 1)
			model.addPower(1);
	}
}
