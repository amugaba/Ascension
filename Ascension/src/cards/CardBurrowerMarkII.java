package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardBurrowerMarkII extends Construct {

	public CardBurrowerMarkII() {
		super();
		name = "Burrower Mark II";
		cost = 3;
		costType = ResourceType.RUNES;
		honor = 3;
		type = CardType.CONSTRUCT;
		faction = CardFaction.MECHANA;
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == GameAction.PLAY_MECHANA_CONSTRUCT && active)
		{
			model.getActivePlayer().drawCard();
			active = false;
		}
	}
}
