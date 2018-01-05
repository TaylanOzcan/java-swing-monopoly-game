package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import domain.PropertyListener;

public class AnimatedText extends JLabel implements Drawable, PropertyListener{
	
	private String text;
	private Color[] colors = new Color[20];
	private int colorIndex = 0;
	private boolean directionX, directionY, directionColor = true;
	
	public AnimatedText(String text) {
		setFont(new Font("Fyodor", Font.BOLD, 50));
		setText(text);
		setColors();
	}

	private void setColors() {
		for(int i = 0; i < 10; i++) {
			colors[i] = new Color(240-i*20, 40, 40 + i*20);
		}
		for(int i = 0; i < 10; i++) {
			colors[10 + i] = new Color(40, 40 + i*20, 240-i*20);
		}
	}
	
	public void setText(String text) {
		this.text = text;
		super.setText(text);
	}
	
	public void setNewLocation() {
		int x, y;
		
		if(this.getWidth() + getX() > 600) directionX = false;
		else if(getX() < 5) directionX = true;
		
		if(this.getHeight() + getY() > 160) directionY = false;
		else if(getY() < 0) directionY = true;
		
		if(directionX) x = getX() + 5;
		else x = getX() - 5;
		
		if(directionY) y = getY() + 5;
		else y = getY() - 5;
		
		super.setLocation(x, y);
	}
	
	public void setNewColor() {
		colorIndex = directionColor? colorIndex+1 : colorIndex-1;
		if(colorIndex == 19) {
			directionColor = false;
		}else if(colorIndex == 0) {
			directionColor = true;
		}
		super.setForeground(colors[colorIndex]);
	}

	@Override
	public void onPropertyEvent(Object source, String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		setNewColor();
		setNewLocation();
	}

}
