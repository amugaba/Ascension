package model;

import java.util.List;

import cards.Card;

public interface IGameModel {

	Card getCenterRowCard(int index);

	boolean canAcquireDefeat(Card card);

	void acquireDefeat(Card card, int index);

	Player getActivePlayer();

	void startTurn();

	void playCard(int index);

	int getRunes();

	int getPower();

	List<Card> getPlayedCards();

	void endTurn();

	void acquireMystic();

	void acquireHeavyInfantry();

	void defeatCultist();

}
