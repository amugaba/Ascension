package model;

import java.util.EnumSet;

public interface GameObserver 
{
	void update(GameModel model, ActionNotice trigger, Object arg);
}
