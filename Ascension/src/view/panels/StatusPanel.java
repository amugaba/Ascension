package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.ViewUtil;

public class StatusPanel extends JPanel 
{
	private JLabel runesLabel;
	private JLabel powerLabel;
	private JLabel honorLabel;
	
	private static final int WIDTH = 192;
	private static final int HEIGHT = 64;

	public StatusPanel(int x, int y) 
	{
		setBounds(x, y, WIDTH, HEIGHT);
		setLayout(new GridLayout(0, 3, 0, 0));
		
		runesLabel = makeLabelWithImage("/assets/Rune.png");
		add(runesLabel);
		
		powerLabel = makeLabelWithImage("/assets/Power.png");
		powerLabel.setForeground(Color.WHITE);
		add(powerLabel);
		
		honorLabel = makeLabelWithImage("/assets/Honor.png");
		honorLabel.setForeground(Color.WHITE);
		add(honorLabel);
		
		setOpaque(false);
	}

	private JLabel makeLabelWithImage(String imagePath)
	{
		JLabel label = new JLabel(new ImageIcon(StatusPanel.class.getResource(imagePath)));
		ViewUtil.setStyle(label, ViewUtil.Style.H3);
		label.setText("");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		return label;
	}

	public void updateStatus(int runes, int power, int honor) 
	{
		runesLabel.setText(String.valueOf(runes));
		powerLabel.setText(String.valueOf(power));
		honorLabel.setText(String.valueOf(honor));
	}
}
