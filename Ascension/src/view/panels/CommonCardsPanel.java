package view.panels;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import view.CardButton;
import view.ViewUtil;
import view.ViewUtil.CardSize;

import model.GameModel;
import controller.GameController;

public class CommonCardsPanel extends CardPanel
{	
	private static final CardSize CARD_SIZE = CardSize.SMALL;

	public CommonCardsPanel(GameController controller, int x, int y, int spacing) 
	{
		super(controller, 3, x, y, spacing, CARD_SIZE);
		
		setCard(0, "Mystic");
		setCard(1, "Heavy Infantry");
		setCard(2, "Cultist");
	}

	@Override
	protected Action buttonAction() {
		return new CardAction();
	}
	
	protected class CardAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int index = ((CardButton)e.getSource()).index;
			if(index == 0)
				controller.buyMystic();
			else if(index == 1)
				controller.buyHeavyInfantry();
			else
				controller.defeatCultist();
		}
	}

}
