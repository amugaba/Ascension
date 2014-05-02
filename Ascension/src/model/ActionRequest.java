package model;

import cards.Card;

public class ActionRequest {
	public enum RequestType {
		SELECT_HAND, SELECT_CENTER, 
		SELECT_CONSTRUCT, SELECT_CENTER_OR_COMMON,
		SELECT_DISCARD, SELECT_HAND_OR_DISCARD,
		SELECT_PLAYED,
		SELECT_OPTION, GAME_OVER, NONE
	}
	
	public RequestType type;
	public Card requester;
	public boolean refusable;
	private GameModel model;
	
	public ActionRequest(RequestType type, Card card, boolean refusable, GameModel model)
	{
		this.type = type;
		this.requester = card;
		this.refusable = refusable;
		this.model = model;
	}
	
	public boolean isArgumentValid(Object arg)
	{
		return requester.isActionArgumentValid(model, type, arg);
	}
	
	public void execute(Object arg)
	{
		requester.execute(model, type, arg);
	}
}
