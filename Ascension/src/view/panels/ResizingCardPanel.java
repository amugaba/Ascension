package view.panels;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import view.CardButton;
import view.ViewUtil;
import view.ViewUtil.CardSize;
import controller.GameController;

public abstract class ResizingCardPanel extends CardPanel {
	
	protected int spacing;
	protected int maxWidth;
	protected int centerX;
	protected int centerY;

	public ResizingCardPanel(GameController controller, int centerX,
			int centerY, int spacing, CardSize cardSize, int maxWidth) {
		super(controller, 1, centerX, centerY, spacing, cardSize);
		this.spacing = spacing;
		this.maxWidth = maxWidth;
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	public void addCard(String name) 
	{
		CardButton button = new CardButton(buttonAction());
		buttons.add(button);
		button.index = buttons.size()-1;
		button.setIcon(ViewUtil.getImage(name, cardSize));
		add(button);
		
		int totalWidth = (ViewUtil.cardWidth(cardSize) + spacing)*buttons.size();
		int widthOverflow = totalWidth - maxWidth;
		int spacingReduction = 0;
		if(widthOverflow > 0)
		{
			spacingReduction = widthOverflow / buttons.size();
			totalWidth = maxWidth;
		}
		
		setBounds(centerX - totalWidth/2, centerY, totalWidth, getBounds().height);
		setLayout(new GridLayout(0, buttons.size(), spacing-spacingReduction, 0));
	}

	public void clearCards() 
	{
		buttons.clear();
		removeAll();
	}
}
