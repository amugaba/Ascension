package cards;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.Player;
import model.ResourceType;

public class CardEarthTyrant extends Card {

	public CardEarthTyrant() {
		super();
		name = "Earth Tyrant";
		cost = 6;
		costType = ResourceType.POWER;
		honor = 5;
		type = CardType.MONSTER;
		faction = CardFaction.MONSTER;
	}
	
	public void onDefeat(GameModel model)
	{
		model.addHonor(honor);
		model.getActivePlayer().drawCard();
		model.getActivePlayer().drawCard();
	}
}
