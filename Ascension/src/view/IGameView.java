package view;

import java.util.List;

import cards.Card;
import controller.GameController;

public interface IGameView {

	void start(GameController controller);

	void setCenterRowCard(int i, String name);

	void addToHand(String name);

	void clearHand();

	void updateStatus(int runes, int power, int honor);

	void updateDeckCounts(int deckSize, int discardSize);

	void addToPlayed(String name);

	void clearPlayed();

	void refresh();

	void setPlayerName(String name);

}
