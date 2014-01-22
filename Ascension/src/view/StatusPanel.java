package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel 
{
	private JLabel runesLabel;
	private JLabel powerLabel;
	private JLabel honorLabel;

	public StatusPanel() 
	{
		setLayout(new GridLayout(0, 3, 0, 0));
		
		runesLabel = makeLabelWithImage("/cards/images/Apprentice.jpg");
		add(runesLabel);
		
		powerLabel = makeLabelWithImage("/cards/images/Apprentice.jpg");
		add(powerLabel);
		
		honorLabel = makeLabelWithImage("/cards/images/Apprentice.jpg");
		add(honorLabel);
	}

	private JLabel makeLabelWithImage(String imagePath)
	{
		JLabel label = new JLabel(new ImageIcon(StatusPanel.class.getResource(imagePath)));
		ViewUtil.setStyle(label, "h3");
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
