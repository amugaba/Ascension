package cards;

import model.GameModel;

public class Construct extends Card 
{	
	boolean active = true; //whether the construct's ability is useable
	public Construct()
	{
		super();
	}
	
	@Override
	public void play(GameModel model)
	{
		model.addObserver(this);
	}
	
	public void onDestroy(GameModel model)
	{
		model.removeObserver(this);
	}
	
	public void useConstruct(GameModel gameModel)
	{
	}
}
