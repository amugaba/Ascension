package cards;

import model.ActionRequest.RequestType;
import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;

public class CardAvataroftheFallen extends Card {

	public CardAvataroftheFallen() {
		super();
		name = "Avatar of the Fallen";
		cost = 7;
		costType = ResourceType.POWER;
		honor = 5;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		model.requestAction(RequestType.SELECT_CENTER, this, false);
	}
	
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;
		model.acquireDefeatFree(card);
	}
	
	public boolean isBanishable()
	{
		return false;
	}
}
