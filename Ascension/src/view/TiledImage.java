package view;

import java.awt.*;  
import java.awt.image.BufferedImage;  
import java.io.*;  

import javax.imageio.ImageIO;  
import javax.swing.*;  
   
public class TiledImage extends JPanel {  
    BufferedImage tileImage;  
   
    public TiledImage(String imagePath) 
    {  
    	try {
			tileImage = ImageIO.read(GameView.class.getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
   
    protected void paintComponent(Graphics g) {  
        int width = getWidth();  
        int height = getHeight();  
        int imageW = tileImage.getWidth(this);  
        int imageH = tileImage.getHeight(this);  
   
        // Tile the image to fill our area.  
        for (int x = 0; x < width; x += imageW) {  
            for (int y = 0; y < height; y += imageH) {  
                g.drawImage(tileImage, x, y, this);  
            }  
        }  
    }  
   
    public Dimension getPreferredSize() {  
        return new Dimension(240, 240);  
    }   
} 