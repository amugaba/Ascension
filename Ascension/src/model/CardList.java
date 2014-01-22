package model;

import java.util.LinkedList;
import java.util.List;

import cards.*;

public class CardList 
{
	public static LinkedList<Card> generateChronicles()
	{
		LinkedList<Card> cards = new LinkedList<Card>();
		cards.add(new CardMystic());
		cards.add(new CardMystic());
		cards.add(new CardMystic());
		cards.add(new CardHeavyInfantry());
		cards.add(new CardHeavyInfantry());
		cards.add(new CardHeavyInfantry());
		cards.add(new CardAvatarGolem());
		cards.add(new CardAvatarGolem());
		cards.add(new CardAvatarGolem());
		cards.add(new CardMystic());
		cards.add(new CardMystic());
		cards.add(new CardMystic());
		cards.add(new CardHeavyInfantry());
		cards.add(new CardHeavyInfantry());
		cards.add(new CardHeavyInfantry());
		cards.add(new CardCorrosiveWidow());
		cards.add(new CardCorrosiveWidow());
		cards.add(new CardCorrosiveWidow());
		return cards;
	}
	
	public static LinkedList<Card> generatePlayerCards()
	{
		LinkedList<Card> cards = new LinkedList<Card>();
		for(int i=0; i<8; i++)
			cards.add(new CardApprentice());
		cards.add(new CardMilitia());
		cards.add(new CardMilitia());
		return cards;
	} 
}
