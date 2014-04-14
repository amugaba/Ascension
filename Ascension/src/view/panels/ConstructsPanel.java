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
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.CardButton;
import view.ViewUtil;
import view.ViewUtil.CardSize;

import model.GameModel;
import controller.GameController;

public class ConstructsPanel extends ResizingCardPanel
{
	private static final CardSize CARD_SIZE = CardSize.SMALL;

	public ConstructsPanel(GameController controller, int centerX, int centerY, int spacing, int maxWidth) 
	{
		super(controller, centerX, centerY, spacing, CARD_SIZE, maxWidth);
	}
	
	public class ConstructAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.clickConstruct(((CardButton)e.getSource()).index);
		}
	}

	@Override
	protected Action buttonAction() {
		return new ConstructAction();
	}

}
