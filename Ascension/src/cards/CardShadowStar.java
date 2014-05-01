package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardShadowStar extends Construct {
	public CardShadowStar() {
		super();
		name = "Shadow Star";
		cost = 3;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.CONSTRUCT;
		faction = CardFaction.VOID;
	}
	
	@Override
	public void play(GameModel model)
	{
		super.play(model);
		model.addPower(1);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == GameAction.TURN_START)
		{
			model.addPower(1);
		}
	}
}
