package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
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
		model.addState(GameState.SELECT_CENTER_OR_COMMON);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if((trigger == GameAction.SELECT_CENTER || trigger == GameAction.SELECT_COMMON) && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.type == CardType.HERO)
			{
				model.removeState(GameState.SELECT_CENTER);
				model.removeState(GameState.SELECT_CENTER_OR_COMMON);
				model.acquireTopDeck(card);
				model.removeObserver(this);
			}
		}
	}
}
