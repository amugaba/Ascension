package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
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
		model.addState(GameState.SELECT_CENTER);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_CENTER && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.removeState(GameState.SELECT_CENTER);
			model.removeObserver(this);
			
			model.acquireDefeatFree(card);
		}
	}
	
	public boolean isBanishable()
	{
		return false;
	}
}
