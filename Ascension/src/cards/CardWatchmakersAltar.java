package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.ResourceType;

public class CardWatchmakersAltar extends Construct {
	public CardWatchmakersAltar() {
		super();
		name = "Watchmaker's Altar";
		cost = 5;
		costType = ResourceType.RUNES;
		honor = 5;
		type = CardType.CONSTRUCT;
		faction = CardFaction.MECHANA;
	}
	
	@Override
	public void play(GameModel model)
	{
		super.play(model);
		model.addMechanaRunes(1);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		super.update(model, trigger, arg);
		
		if(trigger == GameAction.TURN_START)
		{
			model.addMechanaRunes(1);
		}
	}
}
