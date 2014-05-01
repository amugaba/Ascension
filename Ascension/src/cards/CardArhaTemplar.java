package cards;

import java.util.EnumSet;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameException;
import model.GameModel;
import model.GameState;
import model.ResourceType;

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
		model.addState(GameState.SELECT_CENTER_OR_COMMON);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if((trigger == GameAction.SELECT_CENTER || trigger == GameAction.SELECT_COMMON) && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.type == CardType.MONSTER && card.cost <= 4)
			{
				model.removeState(GameState.SELECT_CENTER_OR_COMMON);
				model.removeObserver(this);
				
				model.acquireDefeatFree(card);
			}
		}
	}
}
