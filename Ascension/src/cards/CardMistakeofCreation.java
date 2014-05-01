package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

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
		model.addState(GameState.SELECT_CENTER); //ask to banish in center, then in discard
		model.setRefusable(true);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_CENTER && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.isBanishable())
			{
				model.removeState(GameState.SELECT_CENTER);
				model.addState(GameState.SELECT_DISCARD);
				
				model.moveCard(card, CardLocation.CENTER_VOID);
			}
		}
		if(trigger == GameAction.SELECT_DISCARD && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.removeState(GameState.SELECT_DISCARD);
			model.removeObserver(this);
			model.setRefusable(false);
			
			model.moveCard(card, CardLocation.CENTER_VOID);
		}
		
		//If the action is refused, move to the next step, but don't do the action
		if(trigger == GameAction.REFUSE)
		{
			if(model.getGameState() == GameState.SELECT_CENTER)
			{
				model.removeState(GameState.SELECT_CENTER);
				model.addState(GameState.SELECT_DISCARD);
			}
			else if(model.getGameState() == GameState.SELECT_DISCARD)
			{
				model.removeState(GameState.SELECT_DISCARD);
				model.removeObserver(this);
				model.setRefusable(false);
			}
		}
	}
}
