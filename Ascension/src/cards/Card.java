package cards;

import java.lang.reflect.Method;
import java.util.EnumSet;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.ActionNotice;
import model.GameException;
import model.GameModel;
import model.GameObserver;
import model.Player;
import model.ResourceType;
import model.ActionRequest.RequestType;

public class Card implements GameObserver
{
	public String name;
	public int cost;
	public ResourceType costType;
	public int honor;
	public CardType type;
	protected CardFaction faction;
	public CardLocation	location;
	public Player owner = null;
	
	public static GameModel model;
	
	public Card()	{	}
	
	public void play(GameModel model)	{	}

	public void onDefeat(GameModel gameModel) {		}

	public void onAcquire(GameModel gameModel) {	}

	@Override
	public void update(GameModel model, ActionNotice trigger, Object arg) {	}
	
	public EnumSet<CardFaction> getFactions()
	{
		return EnumSet.of(faction);
	}
	
	public boolean isBanishable()
	{
		return true;
	}

	public boolean isActionArgumentValid(GameModel model, RequestType type, Object arg) {
		return true;
	}

	public void execute(GameModel model, RequestType type, Object arg) {
		if(isActionArgumentValid(model, type, arg))
			return;		
	}
}
