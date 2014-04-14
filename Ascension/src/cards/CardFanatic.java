package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameModel;
import model.GameState;
import model.Player;
import model.ResourceType;

public class CardFanatic extends Card {

	public CardFanatic() 
	{
		super();
		name = "Fanatic";
		cost = 2;
		costType = ResourceType.POWER;
		honor = 0;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
		location = CardLocation.COMMON_CARDS;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(1);
	}
}
