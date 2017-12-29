package gui;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import domain.Bot;
import domain.PropertyListener;

public class AnimatedBot extends JLabel implements Drawable, PropertyListener{

	private static final int SIZE = 5;
	private static final ImageIcon[] happyImages = new ImageIcon[SIZE];
	private static final ImageIcon[] boredImages = new ImageIcon[SIZE];
	private static final ImageIcon[] sadImages = new ImageIcon[SIZE];
	private int index = 0;
	private int state = Bot.INITIAL;
	private boolean direction = true;
	private Bot bot;

	public AnimatedBot(Bot bot) {
		setImages();
		this.bot = bot;
		bot.addPropertyListener(this);
	}
	
	private void setImages() {
		ImageIcon image;
		for(int i = 0; i < 5; i++) {
			image = new ImageIcon("bot_happy_" + (i+1) + ".png");
			happyImages[i] = image;
			image = new ImageIcon("bot_bored_" + (i+1) + ".png");
			boredImages[i] = image;
		}
	}

	@Override
	public synchronized void draw() {
		switch(state) {
		case Bot.INITIAL: this.setIcon(happyImages[index]);break;
		case Bot.HAPPY: this.setIcon(happyImages[index]);break;
		case Bot.SAD: this.setIcon(sadImages[index]);break;
		case Bot.BORED: this.setIcon(boredImages[index]);break;
		}
		index = direction? index+1 : index-1;
		if(index == 4) {
			direction = false;
		}else if(index == 0) {
			direction = true;
		}
	}

	@Override
	public void onPropertyEvent(Object source, String name, Object value) {
		if(source.getClass() == Bot.class) {
			if(name.equals("stateChange")) {
				state = (int) value;
				index = 0;
				direction = true;
			}
		}
	}

}
