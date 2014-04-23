package cards;

import java.util.EnumSet;

import model.CardFaction;
import model.GameAction;
import model.GameModel;

public class Construct extends Card 
{	
	public boolean active = true; //whether the construct's ability is useable
	public Construct()
	{
		super();
	}
	
	@Override
	public void play(GameModel model)
	{
		model.addObserver(this);
	}
	
	public void onDestroy(GameModel model)
	{
		model.removeObserver(this);
	}
	
	@Override
	public void update(GameModel model, GameAction trigger, Object arg) 
	{	
		if(trigger == GameAction.TURN_START)
		{
			active = true;
		}
	}
	
	public void use(GameModel gameModel)
	{
	}
	
	public EnumSet<CardFaction> getFactions()
	{
		for(Card c : model.getActivePlayer().getConstructs())
			if(c.name.equals("Hedron Link Device"))
				return EnumSet.of(faction, CardFaction.MECHANA);
		
		return EnumSet.of(faction);
	}
}
