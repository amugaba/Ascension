package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardYggdrasilStaff extends Construct {
	public CardYggdrasilStaff() {
		super();
		name = "Yggdrasil Staff";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.CONSTRUCT;
		faction = CardFaction.LIFEBOUND;
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
	
	@Override
	public void use(GameModel model)
	{
		if(active && model.getRunes() >= 4)
		{
			model.addRunes(-4);
			model.addHonor(3);
			active = false;
		}
	}
}
