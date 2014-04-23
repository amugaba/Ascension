package controller;

import cards.Card;
import cards.CardCultist;
import cards.CardHeavyInfantry;
import cards.CardMystic;
import model.CardLocation;
import model.GameException;
import model.GameModel;
import model.GameState;
import model.Player;
import view.GameView;

public class GameController 
{
	GameView view;
	GameModel model;
	//when the model executes an action, sometimes it realizes that it has to pause and ask the player to do another action
	//in those cases, it sets forceAction to true and sets an GameState enum
	//the controller sess that when it refreshes the view after doing an action
	//the controller disables/enables some stuff in the view and won't accept certain function calls
	//until it reaches another refreshView where forceAction has been set by the model back to false
	

	public GameController(GameView view, GameModel model) 
	{
		this.view = view;
		this.model = model;
	}

	public void start() 
	{
		view.start(this);
		model.startTurn();
		refreshView();
	}

	public void clickCenterRow(int index)
	{
		Card card = model.getCenterRow().get(index);
		//default action is to acquire/defeat card
		if(model.getGameState() == GameState.NONE && model.canAcquireDefeat(card))
		{
			model.acquireDefeat(card);
		}
		if(model.getGameState() == GameState.SELECT_CENTER || model.getGameState() == GameState.SELECT_CENTER_OR_COMMON)
		{
			model.selectCard(card);
		}
		refreshView();
	}
	
	public void clickHand(int index)
	{
		Card card = model.getActivePlayer().getHandCard(index);
		if(model.getGameState() == GameState.NONE)
		{
			model.playCard(card);
		}
		else if(model.getGameState() == GameState.SELECT_HAND)
		{
			model.selectCard(card);
		}
		refreshView();
	}
	
	public void clickCommon(int index)
	{
		Card card = model.getCommonCards().get(index);
		if(model.getGameState() == GameState.NONE && model.canAcquireDefeat(card))
		{
			model.acquireDefeat(card);
		}
		else if(model.getGameState() == GameState.SELECT_CENTER_OR_COMMON)
		{
			model.selectCard(card);
		}
		refreshView();
	}

	public void clickConstruct(int index) 
	{
		Card card = model.getActivePlayer().getConstructs().get(index);
		if(model.getGameState() == GameState.NONE)
		{
			model.useConstruct(card);
		}
		if(model.getGameState() == GameState.SELECT_CONSTRUCT)
		{
			model.selectCard(card);
		}
		refreshView();
	}
	
	public void clickPlayed(int index) 
	{
		if(model.getGameState() == GameState.NONE)
		{
			// TODO Auto-generated method stub
		}
		refreshView();
	}
	
	public void refreshView()
	{
		Player player = model.getActivePlayer();
		
		//Player name
		view.setPlayerName(player.getName());
		
		//Center row
		for(int i = 0; i<GameModel.CENTER_ROW_SIZE; i++)
		{
			Card card = model.getCenterRow().get(i);
			view.setCenterRowCard(i,card.name);
		}
		
		//Player hand
		view.clearHand();
		for(Card card : player.getHand())
		{
			view.addToHand(card.name); //hand and played panel is disappearing when banishing a card
		}
		
		//Status panel
		view.updateStatus(model.getRunes(), model.getPower(), player.getHonor());
		
		//Deck counts
		view.updateDeckCounts(model.getCards(CardLocation.PLAYER_DECK).size(), 
				model.getCards(CardLocation.PLAYER_DISCARD).size(), 
				model.getCards(CardLocation.CENTER_DECK).size(), 
				model.getCards(CardLocation.CENTER_VOID).size());
		
		//Played cards
		view.clearPlayed();
		for(Card card : model.getPlayedCards())
		{
			view.addToPlayed(card.name);
		}
		
		//Constructs
		view.clearConstructs();
		for(Card card : model.getActivePlayer().getConstructs())
		{
			view.addToConstructs(card.name);
		}
		
		view.refresh();
	}

	public void endTurn() 
	{
		if(model.getGameState() == GameState.NONE)
		{
			model.endTurn();
			model.startTurn();
		}
		refreshView();
	}

	public void clickOption(int i) 
	{
		if(model.getGameState() == GameState.SELECT_OPTION)
		{
			model.selectOption(i);
		}
		refreshView();
	}
}
