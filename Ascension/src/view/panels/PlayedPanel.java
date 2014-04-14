package view.panels;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import view.CardButton;
import view.ViewUtil;
import view.ViewUtil.CardSize;

import controller.GameController;

public class PlayedPanel extends ResizingCardPanel
{	
	private static final CardSize CARD_SIZE = CardSize.MEDIUM;

	public PlayedPanel(GameController controller, int centerX, int centerY, int spacing, int maxWidth) 
	{
		super(controller, centerX, centerY, spacing, CARD_SIZE, maxWidth);
	}
	
	public class PlayedAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.clickPlayed(((CardButton)e.getSource()).index);
		}
	}

	@Override
	protected Action buttonAction() {
		return new PlayedAction();
	}
}
