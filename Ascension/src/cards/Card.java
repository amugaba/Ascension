package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class Card implements ICard
{
	public String name;
	public int cost;
	public ResourceType costType;
	public int honor;
	public CardType type;
	public CardFaction faction;
	
	public Card()
	{
		
	}
	
	public void play(GameModel model)
	{
		
	}

	public void onDefeat(GameModel gameModel) {
		// TODO Auto-generated method stub
		
	}

	public void onAcquire(GameModel gameModel) {
		// TODO Auto-generated method stub
		
	}
}
