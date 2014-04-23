package model;

import java.util.LinkedList;
import java.util.List;

import cards.*;

public class CardList 
{
	public static LinkedList<Card> generateChronicles()
	{
		LinkedList<Card> cards = new LinkedList<Card>();
		cards.add(new CardArbiterofthePrecepice());
		cards.add(new CardArbiterofthePrecepice());
		cards.add(new CardArhaInitiate());
		cards.add(new CardArhaInitiate());
		cards.add(new CardArhaInitiate());
		cards.add(new CardArhaTemplar());
		cards.add(new CardArhaTemplar());
		cards.add(new CardAsceticoftheLidlessEye());
		cards.add(new CardAsceticoftheLidlessEye());
		cards.add(new CardAvatarGolem());
		cards.add(new CardAvatarGolem());
		cards.add(new CardAvataroftheFallen());
		cards.add(new CardBurrowerMarkII());
		cards.add(new CardBurrowerMarkII());
		cards.add(new CardCetraWeaverofStars());
		cards.add(new CardCorrosiveWidow());
		cards.add(new CardCorrosiveWidow());
		cards.add(new CardCorrosiveWidow());
		cards.add(new CardCorrosiveWidow());
		cards.add(new CardDemonSlayer());
		cards.add(new CardDemonSlayer());
		cards.add(new CardDruidsoftheStoneCircle());
		cards.add(new CardDruidsoftheStoneCircle());
		cards.add(new CardEarthTyrant());
		cards.add(new CardEarthTyrant());
		cards.add(new CardEmriOnewiththeVoid());
		cards.add(new CardFlytrapWitch());
		cards.add(new CardFlytrapWitch());
		cards.add(new CardHedronCannon());
		cards.add(new CardHedronLinkDevice());
		cards.add(new CardKortheFerromancer());
		cards.add(new CardLandtalker());
		cards.add(new CardLifeboundInitiate());
		cards.add(new CardLifeboundInitiate());
		cards.add(new CardLifeboundInitiate());
		cards.add(new CardMasterDhartha());
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

	public static LinkedList<Card> generateCommonCards() 
	{
		LinkedList<Card> cards = new LinkedList<Card>();
		cards.add(new CardMystic());
		cards.add(new CardHeavyInfantry());
		cards.add(new CardCultist());
		return cards;
	} 
}
