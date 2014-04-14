package model;

import java.util.EnumSet;

public interface GameObserver 
{
	void update(GameModel model, GameAction trigger, Object arg);
}
