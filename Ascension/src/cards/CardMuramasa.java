package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardMuramasa extends Construct {
	
	public CardMuramasa() {
		super();
		name = "Muramasa";
		cost = 7;
		costType = ResourceType.RUNES;
		honor = 4;
		type = CardType.CONSTRUCT;
		faction = CardFaction.VOID;
	}
	
	@Override
	public void play(GameModel model)
	{
		super.play(model);
		model.addPower(3);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == GameAction.TURN_START)
		{
			model.addPower(3);
		}
	}
}
