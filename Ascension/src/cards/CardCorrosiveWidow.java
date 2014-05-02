package cards;

import java.util.ArrayList;
import java.util.List;

import model.CardFaction;
import model.CardType;
import model.ActionNotice;
import model.GameModel;
import model.Player;
import model.ResourceType;
import model.ActionRequest.RequestType;

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
			model.requestAction(RequestType.SELECT_CONSTRUCT, this, false);
			model.switchPlayer(playersToDiscard.remove(0));
		}
	}
	
	@Override
	public boolean isActionArgumentValid(GameModel model, RequestType type, Object arg) 
	{
		if(type == RequestType.SELECT_CONSTRUCT && arg instanceof Card)
		{
			Card card = (Card) arg;
			if(card.type == CardType.CONSTRUCT)
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void execute(GameModel model, RequestType type, Object arg) 
	{
		super.execute(model, type, arg);
		Card card = (Card) arg;			
		model.destroyConstruct(card);
		
		if(playersToDiscard.size() > 0)
		{
			model.requestAction(RequestType.SELECT_CONSTRUCT, this, false);
			model.switchPlayer(playersToDiscard.remove(0));
		}
		else
		{
			model.resumeActivePlayer();
		}
	}
}
