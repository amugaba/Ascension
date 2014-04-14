package model;

import java.util.Arrays;
import java.util.List;

import cards.Card;

public class DeckArray 
{
	private Card[] cards;
	private CardLocation location;
	
	public DeckArray(int size, CardLocation location)
	{
		cards = new Card[size];
		this.location = location;
	}
	
	public Card get(int i)
	{
		return cards[i];
	}
	
	public Card remove(int i)
	{
		Card card = get(i);
		cards[i] = null;
		return card;
	}
	
	public boolean remove(Card card)
	{
		for(int i = 0; i < cards.length; i++)
		{
			if(cards[i].equals(card))
			{
				cards[i] = null;
				return true;
			}
		}
		return false;
	}
	
	public boolean full()
	{
		for(int i = 0; i < cards.length; i++)
			if(cards[i] == null)
				return false;
		return true;
	}
	
	public boolean add(Card card)
	{
		for(int i = 0; i < cards.length; i++)
			if(cards[i] == null)
			{
				cards[i] = card;
				card.location = location;
				return true;
			}
		return false;
	}
	
	public List<Card> getCards()
	{
		return Arrays.asList(cards);
	}
}
