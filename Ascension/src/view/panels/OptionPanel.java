package view.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.CardButton;
import view.GameView;
import view.ViewUtil;

import controller.GameController;

public class OptionPanel extends JPanel {

	protected GameController controller;
	protected JButton button1;
	protected JButton button2;
	
	protected static int WIDTH = 100;
	protected static int HEIGHT = 50;
	
	protected static int BUTTON_WIDTH = 100;
	protected static int BUTTON_HEIGHT = 100;
	
	public OptionPanel(final GameController controller, int x, int y) {
		this.controller = controller;
		setBounds(x, y, WIDTH, HEIGHT);
		setLayout(null);
		
		button1 = new JButton();
		button1.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		add(button1);
		button1.addActionListener(new OptionAction(0));
		button1.setIcon(new ImageIcon(GameView.class.getResource("/assets/Rune.png")));
		ViewUtil.setStyle(button1, ViewUtil.Style.H3);
		button1.setForeground(Color.BLACK);
		button1.setText("1");
		button1.setHorizontalTextPosition(JLabel.CENTER);
		button1.setVerticalTextPosition(JLabel.CENTER);
		
		button2 = new JButton();
		button2.setBounds(BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		add(button2);
		button2.addActionListener(new OptionAction(1));
		button2.setIcon(new ImageIcon(GameView.class.getResource("/assets/Power.png")));
		ViewUtil.setStyle(button2, ViewUtil.Style.H3);
		button2.setForeground(Color.WHITE);
		button2.setText("1");
		button2.setHorizontalTextPosition(JLabel.CENTER);
		button2.setVerticalTextPosition(JLabel.CENTER);

		setOpaque(false);
	}
	
	protected class OptionAction extends AbstractAction
	{
		private int optionNum;
		public OptionAction(int i) {
			optionNum = i;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			controller.clickOption(optionNum);
		}
	}
}
