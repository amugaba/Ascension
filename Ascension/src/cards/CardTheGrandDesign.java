package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardTheGrandDesign extends Construct {
	public CardTheGrandDesign() {
		super();
		name = "The Grand Design";
		cost = 6;
		costType = ResourceType.RUNES;
		honor = 6;
		type = CardType.CONSTRUCT;
		faction = CardFaction.MECHANA;
	}
	
	@Override
	public void play(GameModel model)
	{
		super.play(model);
		model.addMechanaRunes(2);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == GameAction.TURN_START)
		{
			model.addMechanaRunes(2);
		}
	}
}
