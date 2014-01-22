package view;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import controller.GameController;

public class PlayedPanel extends JPanel
{
	private List<CardButton> buttons;
	private GameController controller;
	
	private static final int PANEL_CENTER_X = 500;
	private static final int PANEL_Y = 300;
	private static final int PANEL_WIDTH_MAX = 700;
	private static final int PANEL_HEIGHT = ViewUtil.CARD_HEIGHT_MED;
	private static final int SPACING = 5;

	public PlayedPanel(GameController controller) 
	{
		this.controller = controller;
		setBounds(PANEL_CENTER_X, PANEL_Y, ViewUtil.CARD_WIDTH_MED, PANEL_HEIGHT);
		setLayout(new GridLayout(0, 1, SPACING, 0));
		buttons = new LinkedList<CardButton>();
	}

	public void addToCards(String name) 
	{
		CardButton button = new CardButton(new PlayedAction());
		buttons.add(button);
		button.index = buttons.size()-1;
		button.setIcon(ViewUtil.getImageMedium(name));
		add(button);
		
		int totalWidth = (ViewUtil.CARD_WIDTH_MED + SPACING)*buttons.size();
		int widthOverflow = totalWidth - PANEL_WIDTH_MAX;
		int spacingReduction = 0;
		if(widthOverflow > 0)
		{
			spacingReduction = widthOverflow / buttons.size();
			totalWidth = PANEL_WIDTH_MAX;
		}

		setBounds(PANEL_CENTER_X - totalWidth/2, PANEL_Y, totalWidth, PANEL_HEIGHT);
		setLayout(new GridLayout(0, buttons.size(), SPACING-spacingReduction, 0));	
	}
	
	public class PlayedAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.clickPlayed(((CardButton)e.getSource()).index);
		}
	}

	public void clearCards() 
	{
		buttons.clear();
		removeAll();
	}
}
