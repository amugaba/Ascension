package model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import model.ActionRequest.RequestType;

import cards.*;

public class GameModel
{
	private Deck centerDeck;
	private List<Player> players;
	private DeckArray centerRow;
	private Deck voidDeck;
	private Deck playedCards;
	private Deck commonCards;
	
	public static final int CENTER_ROW_SIZE = 6;
	private static final int NUM_PLAYERS = 2;
	private static final int HAND_SIZE = 5;
	
	private int runesAvailable = 0;
	private int powerAvailable = 0;
	private int constructRunes = 0;
	private int mechanaRunes = 0;
	
	private int honorPool;
	private static final int HONOR_PER_PLAYER = 30;
	
	private Player activePlayer;
	private Player temporaryPlayer = null;
	
	private List<ActionRequest> actionStack = new ArrayList<ActionRequest>();
	private List<GameObserver> observers = new ArrayList<GameObserver>();
	
	private Player winner;
	private boolean gameEnding = false;
	private boolean gameEnded = false;
	private boolean extraTurn = false;
	
	/**
	 * Constructors
	 */
	public GameModel()
	{
		Card.model = this;
		centerDeck = new Deck(CardList.generateChronicles(), CardLocation.CENTER_DECK);
		centerDeck.shuffle();
		
		centerRow = new DeckArray(CENTER_ROW_SIZE, CardLocation.CENTER_ROW);
		fillCenterRow();
		
		players = new ArrayList<Player>();
		for(int i=0; i<NUM_PLAYERS; i++)
		{
			players.add(new Player(this, "Player " + String.valueOf(i+1)));
		}
		honorPool = HONOR_PER_PLAYER * NUM_PLAYERS;
		
		voidDeck = new Deck(CardLocation.CENTER_VOID);
		playedCards = new Deck(CardLocation.PLAYED_CARDS);
		commonCards = new Deck(CardList.generateCommonCards(), CardLocation.COMMON_CARDS);
		
		activePlayer = players.get(0);
	}

	/**
	 * Resources
	 */
	public void addRunes(int i) 
	{
		runesAvailable += i;
	}
	public void addPower(int i) 
	{
		powerAvailable += i;
	}
	public void addHonor(int i) 
	{
		activePlayer.addHonor(i);
		honorPool -= i;
		if(honorPool < 0)
		{
			honorPool = 0;
			gameEnding = true;
		}
	}
	public int getRunes() 
	{
		return runesAvailable;
	}
	public int getPower() 
	{
		return powerAvailable;
	}
	public int getHonorPool()
	{
		return honorPool;
	}
	public void addConstructRunes(int i) 
	{
		constructRunes += i;
	}
	public void addMechanaRunes(int i) 
	{
		mechanaRunes += i;
	}
	
	/**
	 * Players
	 */
	public Player getActivePlayer() 
	{
		return activePlayer;
	}
	public Player getNextPlayer()
	{
		int index = (players.indexOf(activePlayer) + 1) % players.size();
		return players.get(index);
	}
	public List<Player> getPlayers()
	{
		return players;
	}
	public void switchPlayer(Player p) {
		if(temporaryPlayer == null)
			temporaryPlayer = activePlayer;
		activePlayer = p;
	}
	public void resumeActivePlayer() {
		activePlayer = temporaryPlayer;
		temporaryPlayer = null;
	}
	
	/**
	 * Decks
	 */
	public DeckArray getCenterRow()
	{
		return centerRow;
	}
	public Deck getPlayedCards() 
	{
		return playedCards;
	}
	public Deck getVoidCards()
	{
		return voidDeck;
	}
	public Deck getCommonCards()
	{
		return commonCards;
	}
	public Deck getCenterDeck()
	{
		return centerDeck;
	}
	
	/**
	 * Playing cards
	 */
	public void playCard(Card card)
	{
		card.play(this);
		if(card.type == CardType.CONSTRUCT)
		{
			moveCard(card, CardLocation.PLAYER_CONSTRUCTS);
			if(card.getFactions().contains(CardFaction.MECHANA))
				notifyObservers(ActionNotice.MECHANA_CONSTRUCT_PLAYED, card);
		}
		else
		{
			moveCard(card, CardLocation.PLAYED_CARDS);
			notifyObservers(ActionNotice.HERO_PLAYED, card);
		}
	}
	public void useConstruct(Card card) 
	{
		Construct construct = (Construct) card;
		if(construct.active)
			construct.use(this);
	}
	
	
	/**
	 * Acquire/Defeat cards
	 */
	public boolean canAcquireDefeat(Card card) 
	{
		if(card.costType == ResourceType.POWER && powerAvailable >= card.cost)
			return true;
		
		int totalRunes = runesAvailable;
		
		//Add type-specific runes when applicable
		if(card.type == CardType.CONSTRUCT)
		{
			totalRunes += constructRunes;
			//Add mechana runes too
			if(card.getFactions().contains(CardFaction.MECHANA))
				totalRunes += mechanaRunes;
		}
				
		if(card.costType == ResourceType.RUNES && totalRunes >= card.cost)
			return true;

		return false;
	}
	public void acquireDefeat(Card card) 
	{		
		payFor(card);
		acquireDefeatFree(card);
	}
	public void acquireDefeatFree(Card card)
	{
		if(card.type == CardType.MONSTER)
		{
			card.onDefeat(this);
			if(card.location == CardLocation.CENTER_ROW)
				notifyObservers(ActionNotice.CENTER_DEFEATED, card);
			moveCard(card, CardLocation.CENTER_VOID);
		}
		else
		{
			card.onAcquire(this);
			moveCard(card, CardLocation.PLAYER_DISCARD);
			if(card.type == CardType.CONSTRUCT)
				notifyObservers(ActionNotice.CONSTRUCT_ACQUIRED, card);
		}
	}
	public void acquireTopDeck(Card card) 
	{
		card.onAcquire(this);
		moveCard(card, CardLocation.PLAYER_DECK);
	}
	private void payFor(Card card) 
	{
		if(card.costType == ResourceType.POWER)
			powerAvailable -= card.cost;
		
		
		int totalCost = card.cost;
		
		//Pay for the cost from type-specific runes first
		if(card.type == CardType.CONSTRUCT)
		{
			int runesConsumed;
			
			//Use mechana runes first
			if(card.getFactions().contains(CardFaction.MECHANA))
			{
				runesConsumed = Math.min(totalCost, mechanaRunes);
				totalCost -= runesConsumed;
				mechanaRunes -= runesConsumed;
			}
			
			runesConsumed = Math.min(totalCost, constructRunes);
			totalCost -= runesConsumed;
			constructRunes -= runesConsumed;	
		}
		
		if(card.costType == ResourceType.RUNES)
			runesAvailable -= totalCost;	
	}

	/**
	 * Change turns
	 */
	public void startTurn() 
	{
		for(int i = 0; i < HAND_SIZE; i++)
		{
			getActivePlayer().drawCard();
		}
		//add all construct observers
		for(Card card : activePlayer.getConstructs())
			addObserver(card);
		notifyObservers(ActionNotice.TURN_START, null);
	}
	public void endTurn() 
	{
		notifyObservers(ActionNotice.TURN_END, null);
		
		//remove all construct observers
		for(Card card : activePlayer.getConstructs())
			removeObserver(card);
		
		List<Card> tempFrom = new ArrayList<Card>(getPlayedCards());
		for(Card card : tempFrom)
			moveCard(card, CardLocation.PLAYER_DISCARD);
		
		tempFrom = new ArrayList<Card>(activePlayer.getHand());
		for(Card card : tempFrom)
			moveCard(card, CardLocation.PLAYER_DISCARD);
		
		runesAvailable = 0;
		powerAvailable = 0;
		constructRunes = 0;
		mechanaRunes = 0;
		
		if(!extraTurn)
		{
			int playerIndex = players.indexOf(activePlayer);
			//if the game is ending and the current player is the last player in the turn order
			if(gameEnding && playerIndex == players.size()-1)
			{
				endGame();
			}
	
			activePlayer = getNextPlayer();
		}
	}

	/**
	 * End game
	 */
	private void endGame()
	{
		//add up player honor to determine winner
		int maxHonor = 0;
		Player playerMaxHonor = null;
		for(Player p : players)
		{
			int totalHonor = p.getHonor();
			for(Card card : p.getConstructs())
				totalHonor += card.honor;
			for(Card card : p.getDeck())
				totalHonor += card.honor;
			for(Card card : p.getDiscard())
				totalHonor += card.honor;
			p.addHonor(totalHonor - p.getHonor()); //set their honor as the final total
			
			if(totalHonor >= maxHonor) //later players win ties
			{
				maxHonor = totalHonor;
				playerMaxHonor = p;
			}
			winner = playerMaxHonor;
			
			//move the game to another page
		}
	}
	public Player getWinner()
	{
		return winner;
	}

	/**
	 * Observers
	 */
	public void addObserver(GameObserver obs)
	{
		observers.add(obs);
	}
	public void removeObserver(GameObserver obs)
	{
		observers.remove(obs);
	}
	private void notifyObservers(ActionNotice trigger, Object arg)
	{
		List<GameObserver> temp = new ArrayList<GameObserver>(observers); //bc observers may be removed or added
		for(GameObserver obs : temp)
		{
			obs.update(this, trigger, arg);
		}
	}

	/**
	 * Actions
	 */
	public void requestAction(RequestType type, Card requester, boolean refusable) 
	{
		actionStack.add(new ActionRequest(type, requester, refusable, this));
	}
	public ActionRequest getActionRequested()
	{
		if(actionStack.size() == 0)
			return null;
		return actionStack.get(0);
	}
	public void executeAction(Object arg) 
	{
		ActionRequest action = getActionRequested();
		if(action.isArgumentValid(arg))
		{
			actionStack.remove(action);
			action.execute(arg);
		}
	}
	public void refuseAction()
	{
		if(getActionRequested().refusable)
			actionStack.remove(getActionRequested());
	}
	public List<Card> getPossibleChoices()
	{
		//It'd be nice to label what these actions are going to do
		//acquire, defeat, use construct, banish, discard, destroy construct
		List<Card> actions = new ArrayList<Card>();
		
		ActionRequest action = getActionRequested();
		
		if(action == null)
		{
			for(Card card : centerRow.getCards())
				if(canAcquireDefeat(card))
					actions.add(card);
			for(Card card : commonCards)
				if(canAcquireDefeat(card))
					actions.add(card);
			for(Card card : activePlayer.getConstructs())
				if(((Construct)card).active) //this needs to discriminate between usable and non-usable constructs
					actions.add(card);
			return actions;
		}
		
		switch(action.type)
		{
		case SELECT_CENTER_OR_COMMON: 
			for(Card card : commonCards)
				if(action.isArgumentValid(card))
					actions.add(card);
		case SELECT_CENTER: 
			for(Card card : centerRow.getCards())
				if(action.isArgumentValid(card))
					actions.add(card);
			break;
		case SELECT_CONSTRUCT:
			for(Card card : activePlayer.getConstructs())
				if(action.isArgumentValid(card))
					actions.add(card);
			break;
		case SELECT_HAND:
			for(Card card : activePlayer.getHand())
				if(action.isArgumentValid(card))
					actions.add(card);
			break;
		case SELECT_DISCARD:
			for(Card card : activePlayer.getDiscard())
				if(action.isArgumentValid(card))
					actions.add(card);
			break;
		case SELECT_HAND_OR_DISCARD:
			for(Card card : activePlayer.getHand())
				if(action.isArgumentValid(card))
					actions.add(card);
			for(Card card : activePlayer.getDiscard())
				if(action.isArgumentValid(card))
					actions.add(card);
			break;
		case SELECT_PLAYED:
			for(Card card : playedCards)
				if(action.isArgumentValid(card))
					actions.add(card);
			break;
		case SELECT_OPTION: break;//TBD, only supports Cards right now
		}
		return actions;
	}
	
	/**
	 * Moving cards
	 */
	public void moveCard(Card card, CardLocation newLocation)
	{
		moveCard(card, newLocation, getActivePlayer());
	}
	public void moveCard(Card card, CardLocation newLocation, Player newOwner)
	{
		//Remove from the old location
		CardLocation prevLoc = card.location;
		switch(prevLoc)
		{
		case PLAYER_HAND: card.owner.getHand().remove(card); break;
		case PLAYER_DISCARD: card.owner.getDiscard().remove(card); break;
		case PLAYER_CONSTRUCTS: card.owner.getConstructs().remove(card); break;
		case PLAYED_CARDS: playedCards.remove(card); break;
		case CENTER_ROW: centerRow.remove(card); fillCenterRow(); break;
		case COMMON_CARDS: int index = commonCards.indexOf(card);
						   commonCards.remove(card);
			try {
				commonCards.add(index, card.getClass().newInstance());//replace card with a copy of it
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}break; 
		default: throw new IllegalArgumentException("Unimplemented: Moving from " + prevLoc.toString());
		}
		
		if(EnumSet.of(CardLocation.PLAYER_HAND, CardLocation.PLAYER_DECK, 
				CardLocation.PLAYER_DISCARD, CardLocation.PLAYER_CONSTRUCTS).contains(newLocation))
		{
			card.owner = newOwner;
		}
		
		if(EnumSet.of(CardLocation.CENTER_ROW, CardLocation.CENTER_DECK, 
				CardLocation.CENTER_VOID, CardLocation.COMMON_CARDS).contains(newLocation))
		{
			card.owner = null;
		}
		
		//Add to the new location and update links
		switch(newLocation)
		{
		case PLAYER_HAND: card.owner.getHand().add(card); break;
		case PLAYER_DECK: card.owner.getDeck().addCardTop(card); break;
		case PLAYER_DISCARD: card.owner.getDiscard().add(card); break;
		case PLAYER_CONSTRUCTS: card.owner.getConstructs().add(card); break;
		case PLAYED_CARDS: playedCards.add(card); break;
		case CENTER_DECK: centerDeck.add(card); break;
		case CENTER_VOID: if(!card.getFactions().contains(CardFaction.BASIC)) voidDeck.add(card); break;
		default: throw new IllegalArgumentException("Unimplemented: Moving to " + newLocation.toString());
		}
		card.location = newLocation;
	}
	private void fillCenterRow() 
	{
		while(!centerRow.full())
		{
			if(centerDeck.size() == 0)
			{
				centerDeck.addAll(voidDeck);
				voidDeck.clear();
				centerDeck.shuffle();
			}
			if(centerDeck.size() > 0)
			{
				centerRow.add(centerDeck.deal());
			}
		}
	}
	/**
	 * Misc
	 */
	public void destroyConstruct(Card card)
	{
		if(card.owner != null && card.owner.getConstructs().contains(card))
		{
			((Construct) card).onDestroy(this);
			moveCard(card, CardLocation.PLAYER_DISCARD, card.owner);
		}
	}
	public void addExtraTurn() 
	{
		extraTurn = true;
	}

}
