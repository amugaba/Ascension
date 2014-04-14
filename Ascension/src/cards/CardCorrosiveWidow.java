package cards;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.Player;
import model.ResourceType;

public class CardCorrosiveWidow extends Card 
{
	public CardCorrosiveWidow()
	{
		super();
		name = "Corrosive Widow";
		cost = 4;
		costType = ResourceType.POWER;
		honor = 0;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(3);
		//show other player's board, require that person to select a construct
		Player player = model.getNextPlayer();
		if(player.getConstructs().size() > 0)
		{
			model.addState(GameState.SELECT_CONSTRUCT);
			model.addObserver(this);
			model.switchPlayer();
		}
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_CONSTRUCT)
		{
			model.destroyConstruct((Card)arg);
			model.removeState(GameState.SELECT_CONSTRUCT);
			model.removeObserver(this);
			model.switchPlayer();
		}
	}
}
