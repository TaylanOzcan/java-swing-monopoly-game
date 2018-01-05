package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Bot implements Runnable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int INITIAL = -1;
	public static final int HAPPY = 0;
	public static final int SAD = 1;
	public static final int BORED = 2;

	private int state = INITIAL;
	private int counter = 0;
	private int time = 0;
	private ArrayList<PropertyListener> propertyListeners;

	public Bot() {
		this.propertyListeners = new ArrayList<PropertyListener>();
		new Thread(this).start();
	}

	public void addPropertyListener(PropertyListener pl) {
		propertyListeners.add(pl);
	}

	private void publishPropertyEvent(String name, Object value) {
		for(PropertyListener pl: propertyListeners) {
			pl.onPropertyEvent(this, name, value);
		}
	}
		
	public synchronized void notified() {
		this.counter = 0;
		this.time = 0;
		if(state!=INITIAL) setState(INITIAL);
		publishPropertyEvent("counterInitialized", null);
	}

	public synchronized void setState(int newState) {
		this.state = newState;
		publishPropertyEvent("stateChange", newState);
	}

	public int getState() {
		return this.state;
	}

	@Override
	public void run() {
		while(counter<30) {
			try {
				Thread.sleep(1000);
				counter++;
				time++;
				publishPropertyEvent("timeChange", time);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Domain Bot Interrupted");
			}
			if(counter==30) {
				this.setState(BORED);
				counter = 0;
			}
		}
	}
}
