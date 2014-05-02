package model;

import java.util.LinkedList;
import java.util.List;

import cards.*;

public class CardList 
{
	public static LinkedList<Card> generateChronicles()
	{
		LinkedList<Card> cards = new LinkedList<Card>();
		/*cards.add(new CardArbiterofthePrecepice());
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
		cards.add(new CardMechanaInitiate());
		cards.add(new CardMechanaInitiate());
		cards.add(new CardMechanaInitiate());
		cards.add(new CardMephit());
		cards.add(new CardMephit());
		cards.add(new CardMephit());
		cards.add(new CardMistakeofCreation());
		cards.add(new CardMistakeofCreation());
		cards.add(new CardMistakeofCreation());
		cards.add(new CardMistakeofCreation());*/
		
		//Enlightened cards - 10 types, 18 total
		addCard(CardArhaInitiate.class, 3, cards);
		addCard(CardArhaTemplar.class, 2, cards);
		addCard(CardAsceticoftheLidlessEye.class, 2, cards);
		addCard(CardMasterDhartha.class, 1, cards);
		addCard(CardOziahthePeerless.class, 1, cards);
		addCard(CardSeeroftheForkedPath.class, 3, cards);
		addCard(CardTempleLibrarian.class, 3, cards);
		addCard(CardTwofoldAskara.class, 1, cards);
		addCard(CardTheAllSeeingEye.class, 1, cards);
		addCard(CardTabletofTimesDawn.class, 1, cards);
		
		//Lifebound - 9 types, 18 total
		addCard(CardCetraWeaverofStars.class, 1, cards);
		addCard(CardDruidsoftheStoneCircle.class, 2, cards);
		addCard(CardFlytrapWitch.class, 2, cards);
		addCard(CardLandtalker.class, 1, cards);
		addCard(CardLifeboundInitiate.class, 3, cards);
		addCard(CardRunicLycanthrop.class, 2, cards);
		addCard(CardWolfShaman.class, 3, cards);
		addCard(CardSnapdragon.class, 2, cards);
		addCard(CardYggdrasilStaff.class, 2, cards);
		
		//Mechana - 10 types, 18 total
		addCard(CardAvatarGolem.class, 2, cards);
		addCard(CardKortheFerromancer.class, 1, cards);
		addCard(CardMechanaInitiate.class, 3, cards);
		addCard(CardReactorMonk.class, 2, cards);
		addCard(CardBurrowerMarkII.class, 2, cards);
		addCard(CardTheGrandDesign.class, 2, cards);
		addCard(CardHedronCannon.class, 1, cards);
		addCard(CardHedronLinkDevice.class, 1, cards);
		addCard(CardRocketCourierX99.class, 2, cards);
		addCard(CardWatchmakersAltar.class, 2, cards);
		
		//Void - 9 types, 18 total
		addCard(CardArbiterofthePrecepice.class, 2, cards);
		addCard(CardDemonSlayer.class, 2, cards);
		addCard(CardEmriOnewiththeVoid.class, 1, cards);
		addCard(CardShadeoftheBlackWatch.class, 3, cards);
		addCard(CardSpikeVixen.class, 2, cards);
		addCard(CardVoidInitiate.class, 3, cards);
		addCard(CardMuramasa.class, 1, cards);
		addCard(CardShadowStar.class, 2, cards);
		addCard(CardVoidthirster.class, 2, cards);
		
		//Monsters - 10 types, 28 total
		addCard(CardAvataroftheFallen.class, 1, cards);
		addCard(CardCorrosiveWidow.class, 4, cards);
		addCard(CardEarthTyrant.class, 2, cards);
		addCard(CardMephit.class, 3, cards);
		addCard(CardMistakeofCreation.class, 4, cards);
		addCard(CardSamaelsTrickster.class, 4, cards);
		addCard(CardSeaTyrant.class, 3, cards);
		addCard(CardTormentedSoul.class, 3, cards);
		addCard(CardWindTyrant.class, 3, cards);
		addCard(CardXeronDukeofLies.class, 1, cards);
		
		return cards;
	}
	
	public static void addCard(Class cardType, int num, LinkedList<Card> list)
	{
		for(int i = 0; i < num; i++)
		{
			try {
				list.add((Card) cardType.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
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
