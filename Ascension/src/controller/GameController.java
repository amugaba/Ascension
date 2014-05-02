package controller;

import cards.Card;
import cards.CardCultist;
import cards.CardHeavyInfantry;
import cards.CardMystic;
import model.ActionRequest.RequestType;
import model.ActionRequest;
import model.CardLocation;
import model.GameException;
import model.GameModel;
import model.Player;
import view.GameView;

public class GameController 
{
	GameView view;
	GameModel model;
	
	RequestType actionRequested;
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
		if(actionRequested == RequestType.NONE && model.canAcquireDefeat(card))
		{
			model.acquireDefeat(card);
		}
		if(actionRequested == RequestType.SELECT_CENTER || actionRequested == RequestType.SELECT_CENTER_OR_COMMON)
		{
			model.executeAction(card);
		}
		refreshView();
	}
	
	public void clickHand(int index)
	{
		Card card = model.getActivePlayer().getHandCard(index);
		if(actionRequested == RequestType.NONE)
		{
			model.playCard(card);
		}
		else if(actionRequested == RequestType.SELECT_HAND || actionRequested == RequestType.SELECT_HAND_OR_DISCARD)
		{
			model.executeAction(card);
		}
		refreshView();
	}
	
	public void clickCommon(int index)
	{
		Card card = model.getCommonCards().get(index);
		if(actionRequested == RequestType.NONE && model.canAcquireDefeat(card))
		{
			model.acquireDefeat(card);
		}
		else if(actionRequested == RequestType.SELECT_CENTER_OR_COMMON)
		{
			model.executeAction(card);
		}
		refreshView();
	}

	public void clickConstruct(int index) 
	{
		Card card = model.getActivePlayer().getConstructs().get(index);
		if(actionRequested == RequestType.NONE)
		{
			model.useConstruct(card);
		}
		if(actionRequested == RequestType.SELECT_CONSTRUCT)
		{
			model.executeAction(card);
		}
		refreshView();
	}
	
	public void clickPlayed(int index) 
	{
		Card card = model.getPlayedCards().get(index);
		if(actionRequested == RequestType.NONE)
		{
			// TODO Auto-generated method stub
		}
		if(actionRequested == RequestType.SELECT_PLAYED)
		{
			model.executeAction(card);
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
		view.updateDeckCounts(model.getActivePlayer().getDeck().size(), 
				model.getActivePlayer().getDiscard().size(), 
				model.getCenterDeck().size(), 
				model.getVoidCards().size());
		
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
		
		//Discarded cards
		view.clearDiscard();
		for(Card card : model.getActivePlayer().getDiscard())
		{
			view.addToDiscard(card.name);
		}
		
		view.refresh();
		
		ActionRequest action = model.getActionRequested();
		if(action == null)
		{
			actionRequested = RequestType.NONE;
			view.enableRefuseButton(false);
		}
		else
		{
			actionRequested = action.type;
			view.enableRefuseButton(action.refusable);
		}
	}

	public void endTurn() 
	{
		if(actionRequested == RequestType.NONE)
		{
			model.endTurn();
			model.startTurn();
		}
		refreshView();
	}

	public void clickOption(int i) 
	{
		if(actionRequested == RequestType.SELECT_OPTION)
		{
			model.executeAction(i);
		}
		refreshView();
	}

	public void clickDiscard(int index) 
	{
		Card card = model.getActivePlayer().getDiscard().get(index);
		if(actionRequested == RequestType.NONE)
		{
			//Nothing
		}
		else if(actionRequested == RequestType.SELECT_DISCARD || actionRequested == RequestType.SELECT_HAND_OR_DISCARD)
		{
			model.executeAction(card);
		}
		refreshView();
	}

	public void refuseAction() {
		model.refuseAction();
		refreshView();
	}
}
