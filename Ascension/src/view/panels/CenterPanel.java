package view.panels;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.CardButton;
import view.ViewUtil;
import view.ViewUtil.CardSize;

import controller.GameController;
import model.GameModel;

public class CenterPanel extends CardPanel
{
	private static final CardSize CARD_SIZE = CardSize.LARGE;
	
	public CenterPanel(GameController controller, int numCards, int x, int y,
			int spacing) {
		super(controller, numCards, x, y, spacing, CARD_SIZE);
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
			controller.clickCenterRow(((CardButton)e.getSource()).index);
		}
	}

}
