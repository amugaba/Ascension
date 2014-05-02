package cards;

import model.ActionRequest.RequestType;
import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
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
			model.requestAction(RequestType.SELECT_HAND, this, false);
		}
	}
		
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;			
		model.moveCard(card, CardLocation.PLAYER_DISCARD);
		model.getActivePlayer().drawCard();
		model.getActivePlayer().drawCard();
	}
}
