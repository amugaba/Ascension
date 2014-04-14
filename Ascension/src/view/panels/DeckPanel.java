package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.GameView;
import view.ViewUtil;

public class DeckPanel extends JPanel 
{
	protected JButton button;
	
	private static final int WIDTH = 130;
	private static final int HEIGHT = 177;
	
	public DeckPanel(int x, int y, String imagePath, ActionListener buttonAction) 
	{
		setBounds(x, y, WIDTH, HEIGHT);
		setLayout(null);
		//Card count label
		button = new JButton();
		button.setBounds(0, 0, WIDTH, HEIGHT);
		add(button);
		button.addActionListener(buttonAction);
		button.setIcon(new ImageIcon(GameView.class.getResource(imagePath)));
		ViewUtil.setStyle(button, ViewUtil.Style.H3);
		button.setForeground(Color.WHITE);
		button.setText("");
		button.setHorizontalTextPosition(JLabel.CENTER);
		button.setVerticalTextPosition(JLabel.CENTER);
	}
	
	public void setText(String text)
	{
		button.setText(text);
	}
	
}
