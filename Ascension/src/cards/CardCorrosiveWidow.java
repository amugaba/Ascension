package cards;

import java.util.ArrayList;
import java.util.List;

import model.CardFaction;
import model.CardType;
import model.GameAction;
import model.GameModel;
import model.GameState;
import model.Player;
import model.ResourceType;

public class CardCorrosiveWidow extends Card 
{
	List<Player> playersToDiscard;
	public CardCorrosiveWidow()
	{
		super();
		name = "Corrosive Widow";
		cost = 4;
		costType = ResourceType.POWER;
		honor = 3;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		
		//show each other player's board, require that person to select a construct
		playersToDiscard = new ArrayList<Player>();
		for(Player p : model.getPlayers())
		{
			if(!p.equals(model.getActivePlayer()) && p.getConstructs().size() > 0)
			{
				playersToDiscard.add(p);
			}
		}
		
		//start with the first player
		if(playersToDiscard.size() > 0)
		{
			model.addState(GameState.SELECT_CONSTRUCT);
			model.addObserver(this);
			model.switchPlayer(playersToDiscard.remove(0));
		}
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_CONSTRUCT && arg instanceof Card)
		{
			Card card = (Card) arg;
			model.destroyConstruct(card);
			
			if(playersToDiscard.size() > 0)
			{
				model.switchPlayer(playersToDiscard.remove(0));
			}
			else
			{
				model.removeState(GameState.SELECT_CONSTRUCT);
				model.removeObserver(this);
				model.resumeActivePlayer();
			}
		}
	}
}
