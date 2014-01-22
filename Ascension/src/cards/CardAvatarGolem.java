package cards;

import java.util.ArrayList;
import java.util.List;

import model.CardFaction;
import model.CardType;
import model.GameModel;
import model.ResourceType;

public class CardAvatarGolem extends Card 
{
	public CardAvatarGolem()
	{
		super();
		name = "Avatar Golem";
		cost = 4;
		costType = ResourceType.RUNES;
		honor = 2;
		type = CardType.HERO;
		faction = CardFaction.MECHANA;
	}
	
	public void play(GameModel model)
	{
		model.addPower(2);
		List<Card> constructs = model.getActivePlayer().getConstructs();
		List<CardFaction> factions = new ArrayList<CardFaction>();
		for(Card construct : constructs)
		{
			if(!factions.contains(construct.faction))
				factions.add(construct.faction);
		}
		model.addHonor(factions.size());
	}
}
