package cards;

import java.util.Random;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameModel;
import model.Player;
import model.ResourceType;

public class CardXeronDukeofLies extends Card {	
	public CardXeronDukeofLies()
	{
		super();
		name = "Xeron, Duke of Lies";
		cost = 6;
		costType = ResourceType.POWER;
		honor = 3;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		Random rand = new Random();
		
		//take a card at random from each other player's hand
		for(Player p : model.getPlayers())
		{
			if(!p.equals(model.getActivePlayer()))
			{
				int index = rand.nextInt(p.getHand().size());
				Card card = p.getHand().get(index);
				model.moveCard(card, CardLocation.PLAYER_HAND);
			}
		}
	}
}
