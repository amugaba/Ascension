package controller;

import cards.Card;
import cards.CardCultist;
import cards.CardHeavyInfantry;
import cards.CardMystic;
import model.GameModel;
import model.IGameModel;
import model.Player;
import view.IGameView;

public class GameController 
{
	IGameView view;
	IGameModel model;

	public GameController(IGameView view, IGameModel model) 
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
		//put center row card into player's discard pile
		Card card = model.getCenterRowCard(index);
		if(model.canAcquireDefeat(card))
		{
			model.acquireDefeat(card, index);
			refreshView();
		}
		
	}
	
	public void clickHand(int index)
	{
		model.playCard(index);
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
			Card card = model.getCenterRowCard(i);
			view.setCenterRowCard(i,card.name);
		}
		
		//Player hand
		view.clearHand();
		for(Card card : player.getHand())
		{
			view.addToHand(card.name);
		}
		
		//Status panel
		view.updateStatus(model.getRunes(), model.getPower(), player.getHonor());
		
		//Deck counts
		view.updateDeckCounts(player.deckSize(), player.getDiscard().size());
		
		//Played cards
		view.clearPlayed();
		for(Card card : model.getPlayedCards())
		{
			view.addToPlayed(card.name);
		}
		view.refresh();
	}

	public void endTurn() 
	{
		model.endTurn();
		model.startTurn();
		refreshView();
	}

	public void buyMystic() 
	{
		if(model.canAcquireDefeat(new CardMystic()))
		{
			model.acquireMystic();
			refreshView();
		}
	}
	
	public void buyHeavyInfantry() 
	{
		if(model.canAcquireDefeat(new CardHeavyInfantry()))
		{
			model.acquireHeavyInfantry();
			refreshView();
		}
	}
	
	public void defeatCultist() 
	{
		if(model.canAcquireDefeat(new CardCultist()))
		{
			model.defeatCultist();
			refreshView();
		}
	}

	public void clickPlayed(int index) 
	{
		// TODO Auto-generated method stub
		
	}

	public void clickConstruct(int index) 
	{
		// TODO Auto-generated method stub
		
	}
}
