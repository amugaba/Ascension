package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;
import model.ActionRequest.RequestType;

public class CardShadeoftheBlackWatch extends Card {
	public CardShadeoftheBlackWatch() 
	{
		super();
		name = "Shade of the Black Watch";
		cost = 3;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.VOID;
	}

	public void play(GameModel model)
	{
		model.addPower(2);	
		model.requestAction(RequestType.SELECT_HAND_OR_DISCARD, this, true);
	}
	
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;			
		model.moveCard(card, CardLocation.CENTER_VOID);
	}
}
