package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardMistakeofCreation extends Card {

	private int actionsRemaining = 2;
	
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
		model.addState(GameState.SELECT_DISCARD);
		model.addState(GameState.SELECT_CENTER); //ask to banish in center, then in discard
		//model.enableActionRefusable()
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
				model.moveCard(card, CardLocation.CENTER_VOID);
				actionsRemaining--;
			}
		}
		if(trigger == GameAction.SELECT_DISCARD && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.removeState(GameState.SELECT_DISCARD);
			model.moveCard(card, CardLocation.CENTER_VOID);
			actionsRemaining--;
		}
		
		if(actionsRemaining == 0)
			model.removeObserver(this);
	}
}
