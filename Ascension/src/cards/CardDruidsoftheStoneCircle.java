package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardDruidsoftheStoneCircle extends Card {

	public CardDruidsoftheStoneCircle() {
		super();
		name = "Druids of the Stone Circle";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 3;
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
			if(card.type == CardType.HERO && card.cost <= 3)
			{
				model.removeState(GameState.SELECT_CENTER);
				model.removeState(GameState.SELECT_CENTER_OR_COMMON);
				model.acquireTopDeck(card);
				model.removeObserver(this);
			}
		}
	}
}
