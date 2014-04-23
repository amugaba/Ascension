package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import cards.*;

public class GameModel
{
	private Deck centerDeck;
	private Player[] players;
	private DeckArray centerRow;
	private Deck voidDeck;
	private Deck playedCards;
	private Deck commonCards;
	
	public static final int CENTER_ROW_SIZE = 6;
	private static final int NUM_PLAYERS = 2;
	private static final int HAND_SIZE = 5;
	
	private int runesAvailable = 0;
	private int powerAvailable = 0;
	
	private int honorPool;
	private static final int HONOR_PER_PLAYER = 30;
	private boolean gameEnding = false;
	
	private boolean directAcquire = false;
	private List<CardType> directAcquireTypes;
	private int directAcquireCost = 0;
	
	//State variables
	private int activePlayerIndex = 0;
	private List<GameState> stateStack = new ArrayList<GameState>();
	
	private List<ReplaceRule> replaceRules;
	private List<GameObserver> observers = new ArrayList<GameObserver>();
	
	private Player winner;
	
	
	public GameModel()
	{
		Card.model = this;
		centerDeck = new Deck(CardList.generateChronicles(), CardLocation.CENTER_DECK);
		centerDeck.shuffle();
		
		centerRow = new DeckArray(CENTER_ROW_SIZE, CardLocation.CENTER_ROW);
		fillCenterRow();
		
		players = new Player[NUM_PLAYERS];
		for(int i=0; i<NUM_PLAYERS; i++)
		{
			players[i] = new Player(this, "Player " + String.valueOf(i+1));
		}
		honorPool = HONOR_PER_PLAYER * NUM_PLAYERS;
		
		voidDeck = new Deck(CardLocation.CENTER_VOID);
		playedCards = new Deck(CardLocation.PLAYED_CARDS);
		commonCards = new Deck(CardList.generateCommonCards(), CardLocation.COMMON_CARDS);
		replaceRules = new ArrayList<ReplaceRule>();
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
		players[activePlayerIndex].addHonor(i);
		honorPool -= i;
		if(honorPool < 0)
		{
			honorPool = 0;
			gameEnding = true;
		}
	}
	
	public DeckArray getCenterRow()
	{
		return centerRow;
	}
	
	public boolean canAcquireDefeat(Card card) 
	{
		if(directAcquire && directAcquireTypes.contains(card.type) && directAcquireCost >= card.cost)
			return true;
		else if(card.costType == ResourceType.POWER && powerAvailable >= card.cost)
			return true;
		else if(card.costType == ResourceType.RUNES && runesAvailable >= card.cost)
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
				notifyObservers(GameAction.DEFEAT_CENTER, card);
			if(card.location == CardLocation.COMMON_CARDS)
				notifyObservers(GameAction.DEFEAT_ANY, card);
			moveCard(card, CardLocation.CENTER_VOID);
		}
		else
		{
			card.onAcquire(this);
			moveCard(card, CardLocation.PLAYER_DISCARD);
		}
	}
	
	public void acquireTopDeck(Card card) 
	{
		card.onAcquire(this);
		moveCard(card, CardLocation.PLAYER_DECK);
	}

	private void payFor(Card card) 
	{
		if(directAcquire)
		{
			directAcquire = false;
		}
		else
		{
			if(card.costType == ResourceType.RUNES)
				runesAvailable -= card.cost;
			else if(card.costType == ResourceType.POWER)
				powerAvailable -= card.cost;
		}
	}

	
	public void endTurn() 
	{
		notifyObservers(GameAction.TURN_END, null);
		//remove all construct observers
		//Player player = getActivePlayer();
		for(Card card : getCards(CardLocation.PLAYER_CONSTRUCTS))
			removeObserver(card);
		
		moveAll(CardLocation.PLAYED_CARDS, CardLocation.PLAYER_DISCARD);
		moveAll(CardLocation.PLAYER_HAND, CardLocation.PLAYER_DISCARD);
		
		//if the game is ending and the current player is the last player in the turn order
		if(gameEnding && activePlayerIndex == NUM_PLAYERS-1)
		{
			endGame();
		}

		activePlayerIndex++;
		activePlayerIndex %= players.length;
		runesAvailable = 0;
		powerAvailable = 0;
	}
	
	private void endGame()
	{
		addState(GameState.GAME_OVER);
		
		//add up player honor to determine winner
		int maxHonor = 0;
		Player playerMaxHonor = null;
		for(int i=0; i < NUM_PLAYERS; i++)
		{
			Player p = players[i];
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
		}
	}
	
	public Player getWinner()
	{
		return winner;
	}
	
	public Player getNextPlayer()
	{
		int index = (activePlayerIndex + 1) % players.length;
		return players[index];
	}
	
	
	public void startTurn() 
	{
		for(int i = 0; i < HAND_SIZE; i++)
		{
			getActivePlayer().drawCard();
		}
		//add all construct observers
		for(Card card : getCards(CardLocation.PLAYER_CONSTRUCTS))
			addObserver(card);
		notifyObservers(GameAction.TURN_START, null);
	}

	
	public Player getActivePlayer() 
	{
		return players[activePlayerIndex];
	}

	
	public void playCard(Card card)
	{
		card.play(this);
		if(card.type == CardType.CONSTRUCT)
		{
			moveCard(card, CardLocation.PLAYER_CONSTRUCTS);
			if(card.getFactions().contains(CardFaction.MECHANA))
				notifyObservers(GameAction.PLAY_MECHANA_CONSTRUCT, card);
		}
		else
			moveCard(card, CardLocation.PLAYED_CARDS);
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
	public Deck getPlayedCards() 
	{
		return playedCards;
	}
	
	public GameState getGameState()
	{
		if(stateStack.size() == 0)
			return GameState.NONE;
		return stateStack.get(0);
	}
	
	//The model should be able to generate a list of what actions are possible at each moment
	//Then a player can choose one of those actions
	public void getPlayerActions()
	{
		if(getGameState() == GameState.NONE)
		{
			//TBD
		}
	}

	public void addReplaceRule(ReplaceRule rule) 
	{
		if(!hasReplaceRule(rule))
			replaceRules.add(rule);
	}

	public void removeReplaceRule(ReplaceRule rule) 
	{
		if(hasReplaceRule(rule))
			replaceRules.remove(rule);
	}
	
	private boolean hasReplaceRule(ReplaceRule rule)
	{
		for(ReplaceRule r : replaceRules)
		{
			if(r.name.equals(rule.name))
				return true;
		}
		return false;
	}
	
	public void addObserver(GameObserver obs)
	{
		observers.add(obs);
	}
	
	public void removeObserver(GameObserver obs)
	{
		observers.remove(obs);
	}
	
	private void notifyObservers(GameAction trigger, Object arg)
	{
		List<GameObserver> temp = new ArrayList<GameObserver>(observers); //bc observers may be removed or added
		for(GameObserver obs : temp)
		{
			obs.update(this, trigger, arg);
		}
	}
	
	public void addState(GameState state)
	{
		stateStack.add(state);
	}
	
	public void removeState(GameState state)
	{
		stateStack.remove(state);
	}
	
	public void selectCard(Card card)
	{
		if(getCards(CardLocation.CENTER_ROW).contains(card))
		{
			notifyObservers(GameAction.SELECT_CENTER, card);
		}
		else if(getCards(CardLocation.PLAYER_HAND).contains(card))
		{
			notifyObservers(GameAction.SELECT_HAND, card);
		}
		else if(getCards(CardLocation.PLAYER_CONSTRUCTS).contains(card))
		{
			notifyObservers(GameAction.SELECT_CONSTRUCT, card);
		}
		else if(getCards(CardLocation.COMMON_CARDS).contains(card))
		{
			notifyObservers(GameAction.SELECT_COMMON, card);
		}
	}
	
	public Card getHandCard(int index)
	{
		return getActivePlayer().getHandCard(index);
	}

	public void switchPlayer() {
		//TBD
		activePlayerIndex++;
		activePlayerIndex %= players.length;
	}
	
	public void destroyConstruct(Card card)
	{
		if(getCards(CardLocation.PLAYER_CONSTRUCTS).contains(card))
		{
			((Construct) card).onDestroy(this);
			moveCard(card, CardLocation.PLAYER_DISCARD);
		}
	}

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
		case CENTER_VOID: if(card.getFactions().contains(CardFaction.BASIC)) voidDeck.add(card); break;
		default: throw new IllegalArgumentException("Unimplemented: Moving to " + newLocation.toString());
		}
		card.location = newLocation;
	}
	
	public void moveAll(CardLocation from, CardLocation to)
	{
		List<Card> tempFrom = new ArrayList<Card>(getCards(from));
		for(Card card : tempFrom)
			moveCard(card, to);
	}
	
	public List<Card> getCards(CardLocation location)
	{
		return getCards(location, getActivePlayer());
	}
	
	public List<Card> getCards(CardLocation location, Player player)
	{
		switch(location)
		{
		case PLAYER_HAND: return player.getHand();
		case PLAYER_DECK: return player.getDeck();
		case PLAYER_DISCARD: return player.getDiscard();
		case PLAYER_CONSTRUCTS: return player.getConstructs();
		case PLAYED_CARDS: return playedCards;
		case CENTER_ROW: return centerRow.getCards();
		case CENTER_DECK: return centerDeck;
		case CENTER_VOID: return voidDeck;
		case COMMON_CARDS: return commonCards;
		default: throw new IllegalArgumentException("Unimplemented: Get cards from " + location.toString());
		}
	}

	public Deck getCommonCards() 
	{
		return commonCards;
	}

	public void useConstruct(Card card) 
	{
		Construct construct = (Construct) card;
		if(construct.active)
			construct.use(this);
	}

	public void selectOption(int i) 
	{
		notifyObservers(GameAction.SELECT_OPTION, 1);
	}
}
