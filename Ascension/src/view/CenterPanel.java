package view;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.GameController;
import model.GameModel;

public class CenterPanel extends JPanel
{
	private CardButton[] buttons;
	private GameController controller;
	
	private static final int SPACING = 5;
	private static final int PANEL__X = 50;
	private static final int PANEL_Y = 100;
	private static final int PANEL_WIDTH = (ViewUtil.CARD_WIDTH + SPACING)*6;
	private static final int PANEL_HEIGHT = ViewUtil.CARD_HEIGHT;

	public CenterPanel(GameController controller) 
	{
		this.controller = controller;
		setLayout(new GridLayout(0, GameModel.CENTER_ROW_SIZE, SPACING, 0));
		setBounds(PANEL__X, PANEL_Y, PANEL_WIDTH, PANEL_HEIGHT);
		
		buttons = new CardButton[GameModel.CENTER_ROW_SIZE];
		for(int i=0; i<GameModel.CENTER_ROW_SIZE; i++)
		{
			CardButton button = new CardButton(new CenterRowAction());
			buttons[i] = button;
			button.index = i;
			add(button);
		}
	}
	
	public class CenterRowAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.clickCenterRow(((CardButton)e.getSource()).index);
		}
	}

	public void setCenterRowCard(int i, String name) 
	{
		buttons[i].setIcon(ViewUtil.getImage(name));
	}

}
