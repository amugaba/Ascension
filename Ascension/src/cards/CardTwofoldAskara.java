package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardTwofoldAskara extends Card {
	public CardTwofoldAskara() 
	{
		super();
		name = "Twofold Askara";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.HERO;
		faction = CardFaction.ENLIGHTENED;
	}

	public void play(GameModel model)
	{		
		if(model.getPlayedCards().size() > 0)
		{
			model.addState(GameState.SELECT_PLAYED);
			model.addObserver(this);
		}
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_PLAYED && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.removeState(GameState.SELECT_PLAYED);
			model.removeObserver(this);
			
			card.play(model);
		}
	}
}
