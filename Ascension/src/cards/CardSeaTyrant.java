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

public class CardSeaTyrant extends Card {
	List<Player> playersToPrompt;
	
	public CardSeaTyrant()
	{
		super();
		name = "Sea Tyrant";
		cost = 5;
		costType = ResourceType.POWER;
		honor = 5;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		
		//show each other player's board, require that person to select a construct
		playersToPrompt = new ArrayList<Player>();
		for(Player p : model.getPlayers())
		{
			if(!p.equals(model.getActivePlayer()) && p.getConstructs().size() > 1)
			{
				playersToPrompt.add(p);
			}
		}
		
		//start with the first player
		if(playersToPrompt.size() > 0)
		{
			model.addState(GameState.SELECT_CONSTRUCT);
			model.addObserver(this);
			model.switchPlayer(playersToPrompt.remove(0));
		}
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.SELECT_CONSTRUCT && arg instanceof Card)
		{
			Card card = (Card) arg;
			//destroy all constructs except for the one chosen
			for(Card construct : model.getActivePlayer().getConstructs())
			{
				if(!construct.equals(card))
					model.destroyConstruct(construct);
			}
			
			if(playersToPrompt.size() > 0)
			{
				model.switchPlayer(playersToPrompt.remove(0));
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
