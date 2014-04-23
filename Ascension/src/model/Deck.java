package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cards.Card;

public class Deck extends LinkedList<Card>
{
	private static final long serialVersionUID = 1L;
	private CardLocation location;

	public Deck(CardLocation location) 
	{
		super();
		this.location = location;
	}
	
	public Deck(LinkedList<Card> cardList, CardLocation location) 
	{
		super(cardList);
		this.location = location;
		for(Card card : cardList)
			card.location = location;
	}

	public void shuffle() 
	{
		Collections.shuffle(this);
	}

	public Card deal() 
	{
		return removeFirst();
	}

	public void addAll(List<Card> cards) 
	{
		super.addAll(cards);
		for(Card card : cards)
			card.location = location;
	}

	public boolean add(Card card)
	{
		card.location = location;
		return super.add(card);
	}
	
	public void add(int index, Card card)
	{
		card.location = location;
		super.add(index, card);
	}
	
	public void addCardTop(Card card)
	{
		super.add(0, card);
		card.location = location;
	}
}
