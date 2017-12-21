package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class MonopolyMainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private transient BufferedImage image;
	public MonopolyMainPanel() {
		readImage();
	}
	public void readImage() {
		try{
			image = ImageIO.read(new File("bg_mainPanel.jpg"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}