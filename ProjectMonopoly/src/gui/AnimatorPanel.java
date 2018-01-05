package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AnimatorPanel extends JPanel implements Runnable{

	private static final int SLEEP_TIME = 100;
	private ArrayList<Drawable> elementsToDraw = new ArrayList<Drawable>();
	private boolean animatorStopped = true, firstTime = true;
	private transient BufferedImage image;

	public AnimatorPanel() {
		readImage();
		this.setBorder(BorderFactory.createLineBorder(new Color(30,60,30), 5));
	}
	
	public void readImage() {
		try{
			image = ImageIO.read(new File("bg_animator.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	public void setVisible(boolean display) {
		if (display == true) {
			if (firstTime) {
				firstTime = false;
				// Show the animator.  This starts the GUI thread.
				//animFrame.setVisible(true);
				//this.setOpaque(false);
				// Put the animator in another thread so that the
				// calling object can continue.
				(new Thread(this)).start();
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				System.out.println("Animator Interrupted");
			}
			refreshElements();
		}
	}

	private void refreshElements() {
		for(Drawable d: elementsToDraw) {
			d.draw();
		}
	} 

	public void addDrawable(Drawable d, int loc) {
		elementsToDraw.add(d);
		add(((JLabel) d), loc);
	}
	
	public void addDrawable(Drawable d) {
		elementsToDraw.add(d);
		add(((JLabel) d));
	}

	public void removeDrawable(Drawable d) {
		elementsToDraw.remove(d);
		remove(((JLabel) d));
	}
	
	public void removeAllDrawables() {
		elementsToDraw = new ArrayList<Drawable>();
		removeAll();
	}

}
