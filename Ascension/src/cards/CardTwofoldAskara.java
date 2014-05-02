package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.ResourceType;
import model.ActionRequest.RequestType;

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
			model.requestAction(RequestType.SELECT_PLAYED, this, false);
		}
	}
		
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;			
		card.play(model);
	}
}
