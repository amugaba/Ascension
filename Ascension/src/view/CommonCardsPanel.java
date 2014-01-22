package view;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import model.GameModel;
import controller.GameController;

public class CommonCardsPanel extends JPanel
{
	private CardButton mysticButton;
	private CardButton heavyInfantryButton;
	private CardButton cultistButton;
	
	private GameController controller;
	
	private static final int SPACING = 5;
	private static final int PANEL__X = 800;
	private static final int PANEL_Y = 50;
	private static final int PANEL_WIDTH = (ViewUtil.CARD_WIDTH_SM + SPACING)*3;
	private static final int PANEL_HEIGHT = ViewUtil.CARD_HEIGHT_SM;

	public CommonCardsPanel(GameController controller) 
	{
		this.controller = controller;
		setLayout(new GridLayout(0, 3, SPACING, 0));
		setBounds(PANEL__X, PANEL_Y, PANEL_WIDTH, PANEL_HEIGHT);
		
		mysticButton = new CardButton(new MysticAction());
		mysticButton.setIcon(ViewUtil.getImageSmall("Mystic"));
		add(mysticButton);
		
		heavyInfantryButton = new CardButton(new HeavyInfantryAction());
		heavyInfantryButton.setIcon(ViewUtil.getImageSmall("Heavy Infantry"));
		add(heavyInfantryButton);
		
		cultistButton = new CardButton(new CultistAction());
		cultistButton.setIcon(ViewUtil.getImageSmall("Cultist"));
		add(cultistButton);
	}
	
	public class MysticAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.buyMystic();
		}
	}
	
	public class HeavyInfantryAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.buyHeavyInfantry();
		}
	}
	
	public class CultistAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.defeatCultist();
		}
	}

}
