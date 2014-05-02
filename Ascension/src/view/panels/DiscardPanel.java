package view.panels;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import view.CardButton;
import view.ViewUtil.CardSize;
import controller.GameController;

public class DiscardPanel extends ResizingCardPanel {

	private static final CardSize CARD_SIZE = CardSize.MEDIUM;

	public DiscardPanel(GameController controller, int centerX, int centerY, int spacing, int maxWidth) 
	{
		super(controller, centerX, centerY, spacing, CARD_SIZE, maxWidth);
	}
	
	public class DiscardAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.clickDiscard(((CardButton)e.getSource()).index);
		}
	}

	@Override
	protected Action buttonAction() {
		return new DiscardAction();
	}
}
