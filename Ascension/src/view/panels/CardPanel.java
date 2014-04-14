package view.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import view.CardButton;
import view.ViewUtil;
import view.ViewUtil.CardSize;
import controller.GameController;

public abstract class CardPanel extends JPanel 
{
	protected List<CardButton> buttons;
	protected GameController controller;
	protected CardSize cardSize;

	public CardPanel(GameController controller, int numCards, int x, int y, int spacing, CardSize cardSize) 
	{
		this.controller = controller;
		this.cardSize = cardSize;
		
		setLayout(new GridLayout(0, numCards, spacing, 0));
		setBounds(x, y, (ViewUtil.cardWidth(cardSize) + spacing)*numCards, ViewUtil.cardHeight(cardSize));
		
		buttons = new ArrayList<CardButton>();
		
		for(int i=0; i<numCards; i++)
		{
			CardButton button = new CardButton(buttonAction());
			buttons.add(button);
			button.index = i;
			add(button);
		}
		setOpaque(false);
	}
	
	protected abstract Action buttonAction();
	
	protected class CardAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.clickCenterRow(((CardButton)e.getSource()).index);
		}
	}

	public void setCard(int i, String name) 
	{
		buttons.get(i).setIcon(ViewUtil.getImage(name, cardSize));
	}
}
