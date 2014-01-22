package view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cards.Card;
import controller.GameController;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

public class GameView implements IGameView {

	private JFrame frame;
	private GameController controller;
	private CenterPanel centerRowPanel;
	private HandPanel handPanel;
	private PlayedPanel playedPanel;
	private JPanel centerDeckPanel;
	private StatusPanel statusPanel;

	private JPanel playerDeckPanel;
	private JLabel playerDeckLabel;
	private JPanel playerDiscardPanel;
	private JLabel playerDiscardLabel;
	
	private JLabel playerNameLabel;
	private JButton endTurnButton;
	private CommonCardsPanel commonCardsPanel;

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
	private void initialize(GameController controller) 
	{
		this.controller = controller;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		//background image
		TiledImage background = new TiledImage("/cards/images/Apprentice.jpg");
		background.setBounds(50, 20, 500, 500);
		//frame.getContentPane()
		frame.getLayeredPane().add(background, new Integer(0));
		
		playerNameLabel = new JLabel("Ascension");
		ViewUtil.setStyle(playerNameLabel, "h1");
		playerNameLabel.setBounds(500, 20, 500, 50);
		frame.getLayeredPane().add(playerNameLabel, new Integer(1));
		
		centerRowPanel = new CenterPanel(controller);
		frame.getLayeredPane().add(centerRowPanel, new Integer(1));
		
		handPanel = new HandPanel(controller);
		frame.getLayeredPane().add(handPanel, new Integer(1));
		
		playedPanel = new PlayedPanel(controller);
		frame.getLayeredPane().add(playedPanel, new Integer(1));
		
		centerDeckPanel = new JPanel();
		centerDeckPanel.setBounds(251, 11, ViewUtil.CARD_WIDTH*2, ViewUtil.CARD_HEIGHT);
		frame.getLayeredPane().add(centerDeckPanel, new Integer(1));
		centerDeckPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		commonCardsPanel = new CommonCardsPanel(controller);
		frame.getLayeredPane().add(commonCardsPanel, new Integer(1));
		
		JButton btnNewButton_6 = new JButton("New button");
		centerDeckPanel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		centerDeckPanel.add(btnNewButton_7);
		
		statusPanel = new StatusPanel();
		statusPanel.setBounds(50, 11, 167, 55);
		frame.getLayeredPane().add(statusPanel, new Integer(1));
		
		endTurnButton = new JButton(new endTurnAction());
		endTurnButton.setText("End Turn");
		endTurnButton.setBounds(1100, 400, 167, 55);
		frame.getLayeredPane().add(endTurnButton, new Integer(1));
		
		playerDeckPanel = new JPanel();
		playerDeckPanel.setBounds(10, 500, 130, 217);
		frame.getLayeredPane().add(playerDeckPanel);
		playerDeckPanel.setLayout(null);
		
		JLabel lblPlayerDeck = makeLabel("Deck");
		lblPlayerDeck.setBounds(10, 0, 110, 29);
		playerDeckPanel.add(lblPlayerDeck);
		
		playerDeckLabel = makeLabelWithImage("/cards/images/Apprentice.jpg");
		playerDeckLabel.setBounds(0, 40, 130, 177);
		playerDeckPanel.add(playerDeckLabel);
		
		playerDiscardPanel = new JPanel();
		playerDiscardPanel.setLayout(null);
		playerDiscardPanel.setBounds(1073, 500, 130, 217);
		frame.getLayeredPane().add(playerDiscardPanel, new Integer(1));
		
		JLabel label = makeLabel("Discard");
		label.setBounds(10, 0, 110, 29);
		playerDiscardPanel.add(label);
		
		playerDiscardLabel = makeLabelWithImage("/cards/images/Apprentice.jpg");
		playerDiscardLabel.setBounds(0, 40, 130, 177);
		playerDiscardPanel.add(playerDiscardLabel);

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

	@Override
	public void setCenterRowCard(int i, String name) 
	{
		centerRowPanel.setCenterRowCard(i, name);
	}

	@Override
	public void addToHand(String name) 
	{
		handPanel.addToHand(name);
	}

	@Override
	public void clearHand() 
	{
		handPanel.clearHand();
	}

	@Override
	public void updateStatus(int runes, int power, int honor) 
	{
		statusPanel.updateStatus(runes, power, honor);
	}

	@Override
	public void updateDeckCounts(int deckSize, int discardSize) 
	{
		playerDeckLabel.setText(String.valueOf(deckSize));
		playerDiscardLabel.setText(String.valueOf(discardSize));
	}

	@Override
	public void addToPlayed(String name) 
	{
		playedPanel.addToCards(name);
	}

	@Override
	public void clearPlayed() 
	{
		playedPanel.clearCards();
	}

	@Override
	public void refresh() 
	{
		//frame.revalidate();
		frame.repaint();
	}

	@Override
	public void setPlayerName(String name) 
	{
		playerNameLabel.setText(name + "'s Turn");
	}
}
