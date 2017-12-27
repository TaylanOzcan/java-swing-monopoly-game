package domain;

public class Bot {

	private static final int HAPPY = 0;
	private static final int SAD = 1;
	private static final int BORED = 2;
	
	private int state;
	
	public Bot() {
		this.state = -1;
	}
	
	public void setState(int newState) {
		this.state = newState;
	}
	
	public int getState() {
		return this.state;
	}
}
