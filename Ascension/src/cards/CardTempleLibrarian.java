package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardTempleLibrarian extends Card {
	public CardTempleLibrarian() 
	{
		super();
		name = "Temple Librarian";
		cost = 2;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.ENLIGHTENED;
	}

	public void play(GameModel model)
	{
		//if the player has any cards, ask him to discard one
		if(model.getActivePlayer().getHand().size() > 0)
		{		
			model.addState(GameState.SELECT_HAND);
			model.addObserver(this);
		}
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_HAND && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.removeState(GameState.SELECT_HAND);
			model.removeObserver(this);
			
			model.moveCard(card, CardLocation.PLAYER_DISCARD);
			model.getActivePlayer().drawCard();
			model.getActivePlayer().drawCard();
		}
	}
}
