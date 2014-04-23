package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardMechanaInitiate extends Card {

	public CardMechanaInitiate(){
		super();
		name = "Mechana Initiate";
		cost = 1;
		costType = ResourceType.RUNES;
		honor = 1;
		type = CardType.HERO;
		faction = CardFaction.MECHANA;
	}

	public void play(GameModel model)
	{
		model.addState(GameState.SELECT_OPTION);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_OPTION)
		{
			if((Integer)arg == 0)
				model.addRunes(1);
			else
				model.addPower(1);
			model.removeState(GameState.SELECT_OPTION);
		}
	}
}
