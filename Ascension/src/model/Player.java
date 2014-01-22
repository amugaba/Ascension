package model;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

public class Player 
{
	private String name;
	private Deck deck;
	private List<Card> discardPile;
	private List<Card> hand;
	private List<Card> constructs;
	private int honor;
	
	public Player()
	{
		deck = new Deck(CardList.generatePlayerCards());
		hand = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		constructs = new ArrayList<Card>();
		honor = 0;
		deck.shuffle();
	}
	
	public void addToDiscard(Card card) 
	{
		discardPile.add(card);
	}

	public List<Card> getHand()
	{
		return hand;
	}

	public void drawCard() 
	{
		if(deck.size() == 0)
		{
			deck.addCards(discardPile);
			discardPile.clear();
			deck.shuffle();
		}
		if(deck.size() > 0)
		{
			hand.add(deck.deal());
		}
	}

	public int getHonor() 
	{
		return honor;
	}

	public int deckSize()
	{
		return deck.size();
	}
	
	public List<Card> getDiscard()
	{
		return discardPile;
	}

	public void addHonor(int i) 
	{
		honor += i;
	}

	public List<Card> getConstructs() 
	{
		return constructs;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

}
