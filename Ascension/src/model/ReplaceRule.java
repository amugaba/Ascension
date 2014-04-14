package model;

import java.lang.reflect.Method;

import cards.Card;

//Really these should be listeners for a certain event
//Voidthirster listens for when a monster is defeated in the center row
//When that happens, it adds 1 honor and makes itself inactive
public class ReplaceRule 
{
	public enum RuleType
	{
		ACQUIRE_HERO, PLAY_MECHANA_CONSTRUCT, DEFEAT_MONSTER_CENTER
	}

	public String name;
	public RuleType type;
	public Method replaceMethod;
	public boolean active;
	
	public ReplaceRule(RuleType type, String methodName, Card callingObject)
	{
		Method callback = null;
		for(Method method : callingObject.getClass().getMethods())
		{
			if(method.getName().equals(methodName))
			{
				callback = method;
				break;
			}
		}
		
		replaceMethod = callback;
		this.name = callingObject.name;
		this.type = type;
		active = true;
	}
}
