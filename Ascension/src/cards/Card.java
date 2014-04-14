package cards;

import java.lang.reflect.Method;
import java.util.EnumSet;

import model.CardFaction;
import model.CardLocation;
import model.CardType;
import model.GameAction;
import model.GameException;
import model.GameModel;
import model.GameState;
import model.GameObserver;
import model.Player;
import model.ReplaceRule;
import model.ResourceType;

public class Card implements GameObserver
{
	public String name;
	public int cost;
	public ResourceType costType;
	public int honor;
	public CardType type;
	public CardFaction faction;
	public ReplaceRule rule;
	public CardLocation	location;
	public Player owner = null;
	
	public Card()	{	}
	
	public void play(GameModel model)	{	}

	public void onDefeat(GameModel gameModel) {		}

	public void onAcquire(GameModel gameModel) {	}
	
	public void addReplaceRule(GameModel gameModel)
	{
		gameModel.addReplaceRule(rule);
	}
	
	public void removeReplaceRule(GameModel gameModel)
	{
		gameModel.removeReplaceRule(rule);
	}

	@Override
	public void update(GameModel model, GameAction trigger, Object arg) {	}
}
