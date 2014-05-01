package cards;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardTabletofTimesDawn extends Construct {
	public CardTabletofTimesDawn() {
		super();
		name = "Tablet of Time's Dawn";
		cost = 5;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.CONSTRUCT;
		faction = CardFaction.ENLIGHTENED;
	}
	
	@Override
	public void use(GameModel model)
	{
		model.moveCard(this, CardLocation.CENTER_VOID);
		model.addExtraTurn();
	}
}
