package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cards.Card;

public class Deck 
{
	private LinkedList<Card> cards;

	public Deck(LinkedList<Card> cardList) 
	{
		cards = cardList;
	}

	public void shuffle() 
	{
		Collections.shuffle(cards);
	}

	public Card deal() 
	{
		return cards.removeFirst();
	}

	public int size() 
	{
		return cards.size();
	}

	public void addCards(List<Card> cards) 
	{
		this.cards.addAll(cards);
	}

}
