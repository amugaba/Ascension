package cards;

import java.util.EnumSet;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardVoidthirster extends Construct {

	public CardVoidthirster() {
		super();
		name = "Voidthirster";
		cost = 5;
		costType = ResourceType.RUNES;
		honor = 3;
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
		if(trigger == GameAction.TURN_START)
		{
			model.addPower(1);
			active = true;
		}
		else if(trigger == GameAction.DEFEAT_CENTER && active)
		{
			model.addHonor(1);
			active = false;
		}
	}
}
