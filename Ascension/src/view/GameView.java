package view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cards.Card;
import controller.GameController;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import model.GameModel;

import javax.swing.JLabel;

import view.ViewUtil.CardSize;
import view.panels.CenterPanel;
import view.panels.CommonCardsPanel;
import view.panels.ConstructsPanel;
import view.panels.DeckPanel;
import view.panels.HandPanel;
import view.panels.PlayedPanel;
import view.panels.StatusPanel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class GameView
{
	private static final int CENTER_ROW_X = 175;
	private static final int CENTER_ROW_Y = 200;
	
	private static final int HAND_X = 700;
	private static final int HAND_Y = 675;
	private static final int HAND_WIDTH_MAX = 700;
	
	private static final int COMMON_CARDS_X = 50;
	private static final int COMMON_CARDS_Y = 50;
	
	private static final int PLAYED_X = 700;
	private static final int PLAYED_Y = 475;
	private static final int PLAYED_WIDTH_MAX = 700;
	
	private static final int CONSTRUCTS_X = 200;
	private static final int CONSTRUCTS_Y = 700;
	private static final int CONSTRUCTS_WIDTH_MAX = 300;
	
	private static final int PLAYER_DECK_X = 10;
	private static final int PLAYER_DECK_Y = 500;
	
	private static final int PLAYER_DISCARD_X = 1200;
	private static final int PLAYER_DISCARD_Y = 500;
	
	private static final int CENTER_DECK_X = 1000;
	private static final int CENTER_DECK_Y = 20;
	
	private static final int CENTER_DISCARD_X = 1150;
	private static final int CENTER_DISCARD_Y = 20;
	
	private static final int RESOURCES_X = 700;
	private static final int RESOURCES_Y = 100;
	
	private static final int DEFAULT_SPACING = 5;
	
	
	private JFrame frame;
	private GameController controller;
	
	//Card panels
	private CenterPanel centerRowPanel;
	private HandPanel handPanel;
	private PlayedPanel playedPanel;
	private CommonCardsPanel commonCardsPanel;
	private ConstructsPanel constructsPanel;
	
	//Deck panels
	private DeckPanel centerDeckPanel;
	private DeckPanel centerDiscardPanel;
	private DeckPanel playerDeckPanel;
	private DeckPanel playerDiscardPanel;
	
	//Other
	private StatusPanel statusPanel;
	
	private JLabel playerNameLabel;
	private JButton endTurnButton;

	/**
	 * Launch the application.
	 */
	public void start(GameController controller)
	{
		initialize(controller);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameView() 
	{
		ViewUtil.init();
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final GameController controller) 
	{
		this.controller = controller;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//background image
		TiledImage background = new TiledImage("/assets/Blue_cell_computer_background.jpg");
		background.setBounds(0, 0, 1400, 900);
		frame.getLayeredPane().add(background, new Integer(0));
		
		playerNameLabel = new JLabel("Ascension");
		ViewUtil.setStyle(playerNameLabel, ViewUtil.Style.H1);
		playerNameLabel.setBounds(500, 20, 500, 50);
		frame.getLayeredPane().add(playerNameLabel, new Integer(1));
		
		//Card panels
		centerRowPanel = new CenterPanel(controller, GameModel.CENTER_ROW_SIZE, CENTER_ROW_X, CENTER_ROW_Y, DEFAULT_SPACING);
		frame.getLayeredPane().add(centerRowPanel, new Integer(1));
		
		handPanel = new HandPanel(controller, HAND_X, HAND_Y, HAND_WIDTH_MAX, DEFAULT_SPACING);
		frame.getLayeredPane().add(handPanel, new Integer(1));
		
		playedPanel = new PlayedPanel(controller, PLAYED_X, PLAYED_Y, DEFAULT_SPACING, PLAYED_WIDTH_MAX);
		frame.getLayeredPane().add(playedPanel, new Integer(1));
		
		commonCardsPanel = new CommonCardsPanel(controller, COMMON_CARDS_X, COMMON_CARDS_Y, DEFAULT_SPACING);
		frame.getLayeredPane().add(commonCardsPanel, new Integer(1));
		
		constructsPanel = new ConstructsPanel(controller, CONSTRUCTS_X, CONSTRUCTS_Y, DEFAULT_SPACING, CONSTRUCTS_WIDTH_MAX);
		frame.getLayeredPane().add(constructsPanel, new Integer(1));
		
		//Other
		statusPanel = new StatusPanel(RESOURCES_X, RESOURCES_Y);
		frame.getLayeredPane().add(statusPanel, new Integer(1));
		
		endTurnButton = new JButton(new endTurnAction());
		endTurnButton.setText("End Turn");
		endTurnButton.setBounds(1100, 400, 167, 55);
		frame.getLayeredPane().add(endTurnButton, new Integer(1));
		
		//Deck panels
		playerDeckPanel = new DeckPanel(PLAYER_DECK_X, PLAYER_DECK_Y, "/assets/cardback-green.jpg", 
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						controller.defeatCultist();						
					}	}	);
		frame.getLayeredPane().add(playerDeckPanel, new Integer(1));
		
		playerDiscardPanel = new DeckPanel(PLAYER_DISCARD_X, PLAYER_DISCARD_Y, "/assets/cardback-red.jpg", 
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						controller.defeatCultist();						
					}	}	);
		frame.getLayeredPane().add(playerDiscardPanel, new Integer(1));
		
		centerDeckPanel = new DeckPanel(CENTER_DECK_X, CENTER_DECK_Y, "/assets/cardback.jpg", 
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						controller.defeatCultist();						
					}	}	);
		frame.getLayeredPane().add(centerDeckPanel, new Integer(1));
		
		centerDiscardPanel = new DeckPanel(CENTER_DISCARD_X, CENTER_DISCARD_Y, "/assets/cardback-purple.jpg", 
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						controller.defeatCultist();						
					}	}	);
		frame.getLayeredPane().add(centerDiscardPanel, new Integer(1));
	}
	
	public class endTurnAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.endTurn();
		}
	}
	
	private JLabel makeLabel(String text)
	{
		JLabel label = new JLabel(text);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 28));
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		return label;
	}
	
	private JLabel makeLabelWithImage(String imagePath)
	{
		JLabel label = new JLabel(new ImageIcon(GameView.class.getResource(imagePath)));
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setText("");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		return label;
	}

	public void setCenterRowCard(int i, String name) 
	{
		centerRowPanel.setCard(i, name);
	}

	public void addToHand(String name) 
	{
		handPanel.addCard(name);
	}

	public void clearHand() 
	{
		handPanel.clearCards();
	}

	public void updateStatus(int runes, int power, int honor) 
	{
		statusPanel.updateStatus(runes, power, honor);
	}

	public void updateDeckCounts(int playerDeckSize, int playerDiscardSize, int centerSize, int voidSize) 
	{
		playerDeckPanel.setText(String.valueOf(playerDeckSize));
		playerDiscardPanel.setText(String.valueOf(playerDiscardSize));
		centerDeckPanel.setText(String.valueOf(centerSize));
		centerDiscardPanel.setText(String.valueOf(voidSize));
	}

	public void addToPlayed(String name) 
	{
		playedPanel.addCard(name);
	}

	public void clearPlayed() 
	{
		playedPanel.clearCards();
	}

	public void refresh() 
	{
		frame.revalidate();
		frame.repaint();
	}

	public void setPlayerName(String name) 
	{
		playerNameLabel.setText(name + "'s Turn");
	}

	public void clearConstructs() 
	{
		constructsPanel.clearCards();
	}
	
	public void addToConstructs(String name)
	{
		constructsPanel.addCard(name);
	}
}
