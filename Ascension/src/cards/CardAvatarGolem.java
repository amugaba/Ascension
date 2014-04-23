package cards;

import java.util.ArrayList;
import java.util.EnumSet;
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

		EnumSet<CardFaction> factions = EnumSet.noneOf(CardFaction.class);
		for(Card construct : model.getActivePlayer().getConstructs())
		{
			factions.addAll(construct.getFactions());
		}
		model.addHonor(factions.size());
	}
}
