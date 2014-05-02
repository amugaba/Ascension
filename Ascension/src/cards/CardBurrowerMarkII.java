package cards;

import model.CardFaction;
import model.CardType;
import model.ActionNotice;
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
	public void update(GameModel model, ActionNotice trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == ActionNotice.MECHANA_CONSTRUCT_PLAYED && active)
		{
			model.getActivePlayer().drawCard();
			active = false;
		}
	}
}
