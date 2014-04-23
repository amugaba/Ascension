package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.ResourceType;

public class CardMephit extends Card {

	public CardMephit() {
		super();
		name = "Mephit";
		cost = 3;
		costType = ResourceType.POWER;
		honor = 2;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		model.addState(GameState.SELECT_CENTER);
		model.enableActionRefusable(GameState.SELECT_CENTER);
		model.addObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_CENTER && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.isBanishable())
			{
				model.removeState(GameState.SELECT_CENTER);
				model.moveCard(card, CardLocation.CENTER_VOID);
				model.removeObserver(this);
			}
		}
	}
}
