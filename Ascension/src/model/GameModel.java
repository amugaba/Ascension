package model;

import java.util.ArrayList;
import java.util.List;

import cards.*;

public class GameModel implements IGameModel 
{
	private Deck centerDeck;
	private Player[] players;
	private Card[] centerRow;
	private List<Card> voidPile;
	private List<Card> playedCards;
	
	public static final int CENTER_ROW_SIZE = 6;
	private static final int NUM_PLAYERS = 2;
	private static final int HAND_SIZE = 5;
	
	private int runesAvailable = 0;
	private int powerAvailable = 0;
	
	private boolean directAcquire = false;
	private List<CardType> directAcquireTypes;
	private int directAcquireCost = 0;
	
	private int activePlayerIndex = 0;
	
	public GameModel()
	{
		centerDeck = new Deck(CardList.generateChronicles());
		centerDeck.shuffle();
		
		centerRow = new Card[CENTER_ROW_SIZE];
		fillCenterRow();
		
		players = new Player[NUM_PLAYERS];
		for(int i=0; i<NUM_PLAYERS; i++)
		{
			players[i] = new Player();
			players[i].setName("Player " + String.valueOf(i+1));
		}
		
		voidPile = new ArrayList<Card>();
		playedCards = new ArrayList<Card>();
	}

	private void fillCenterRow() 
	{
		for(int i=0; i<CENTER_ROW_SIZE; i++)
		{
			if(centerRow[i] == null)
			{
				if(centerDeck.size() == 0)
				{
					centerDeck.addCards(voidPile);
					voidPile.clear();
					centerDeck.shuffle();
				}
				if(centerDeck.size() > 0)
				{
					centerRow[i] = centerDeck.deal();
				}
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
	}

	@Override
	public Card getCenterRowCard(int index) 
	{
		return centerRow[index];
	}

	@Override
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

	@Override
	public void acquireDefeat(Card card, int index) 
	{		
		if(card.type == CardType.MONSTER)
		{
			payFor(card);
			card.onDefeat(this);
			voidPile.add(card);
		}
		else
		{
			payFor(card);
			card.onAcquire(this);
			getActivePlayer().addToDiscard(card);
		}
		centerRow[index] = null;
		fillCenterRow();
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

	@Override
	public void endTurn() 
	{
		Player player = getActivePlayer();
		for(Card card : playedCards)
			player.addToDiscard(card);
		for(Card card : player.getHand())
			player.addToDiscard(card);
		playedCards.clear();
		player.getHand().clear();
		activePlayerIndex++;
		activePlayerIndex %= players.length;
		runesAvailable = 0;
		powerAvailable = 0;
	}
	
	@Override
	public void startTurn() 
	{
		for(int i = 0; i < HAND_SIZE; i++)
		{
			getActivePlayer().drawCard();
		}
	}

	@Override
	public Player getActivePlayer() 
	{
		return players[activePlayerIndex];
	}

	@Override
	public void playCard(int index) 
	{
		Card card = getActivePlayer().getHand().remove(index);
		card.play(this);
		playedCards.add(card);
	}

	@Override
	public int getRunes() 
	{
		return runesAvailable;
	}

	@Override
	public int getPower() 
	{
		return powerAvailable;
	}

	@Override
	public List<Card> getPlayedCards() 
	{
		return playedCards;
	}
	
	@Override
	public void acquireMystic() 
	{
		Card card = new CardMystic();
		payFor(card);
		getActivePlayer().addToDiscard(card);
	}

	@Override
	public void acquireHeavyInfantry() 
	{
		Card card = new CardHeavyInfantry();
		payFor(card);
		getActivePlayer().addToDiscard(card);
	}
	
	@Override
	public void defeatCultist() 
	{
		Card card = new CardCultist();
		payFor(card);
		card.onDefeat(this);
	}
}
