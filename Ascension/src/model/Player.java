package model;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

public class Player 
{
	private GameModel model;
	private String name;
	private Deck deck;
	private Deck discardPile;
	private Deck hand;
	private Deck constructs;
	private int honor;
	
	public Player(GameModel model, String name)
	{
		this.name = name;
		this.model = model;
		deck = new Deck(CardList.generatePlayerCards(), CardLocation.PLAYER_DECK);
		for(Card card : deck)
			card.owner = this;
		hand = new Deck(CardLocation.PLAYER_HAND);
		discardPile = new Deck(CardLocation.PLAYER_DISCARD);
		constructs = new Deck(CardLocation.PLAYER_CONSTRUCTS);
		honor = 0;
		deck.shuffle();
	}
	
	public Card getHandCard(int index)
	{
		return hand.get(index);
	}
	
	/*public void addToDiscard(Card card) 
	{
		discardPile.add(card);
	}*/

	public Deck getDeck()
	{
		return deck;
	}
	public Deck getHand()
	{
		return hand;
	}
	public Deck getDiscard()
	{
		return discardPile;
	}
	public Deck getConstructs() 
	{
		return constructs;
	}
	
	public void drawCard() 
	{
		if(deck.size() == 0)
		{
			deck.addAll(discardPile);
			discardPile.clear();
			deck.shuffle();
		}
		if(deck.size() > 0)
		{
			hand.add(deck.deal());
		}
	}
	
	/*public void banishHandCard(Card card)
	{
		if(hand.contains(card))
		{
			hand.remove(card);
			model.addToVoid(card);
		}
	}*/

	public int getHonor() 
	{
		return honor;
	}
	
	

	public void addHonor(int i) 
	{
		honor += i;
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
