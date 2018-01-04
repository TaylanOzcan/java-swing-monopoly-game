package domain;

import java.awt.Color;

import javax.swing.JLabel;

import gui.Drawable;

public class AnimatedTimer extends JLabel implements Drawable, PropertyListener{

	private Bot bot;
	private int time=0;
	
	public AnimatedTimer(Bot bot) {
		this.bot = bot;
		bot.addPropertyListener(this);
		setBackground(Color.GREEN);
	}
	
	@Override
	public void draw() {
		setText("" + time);
	}
	
	@Override
	public void onPropertyEvent(Object source, String name, Object value) {
		if(source.getClass() == Bot.class) {
			if(name.equals("timeChange")) {
				time = (int) value;
			}else if(name.equals("timeInitialized")) {
				time = 0;
			}
		}
	}
}
