package domain;
import java.util.Random;

public class RegularDie implements Die {
	
	private int faceValue;
	Random rnd;
	
	public RegularDie(){
		this.rnd = new Random();
	}

	@Override
	public int getValue() {
		// REQUIRES : The Die.
		
		// EFFECTS : Returns the die's face value.
		faceValue = rnd.nextInt(6) + 1;
		return faceValue;
	}
	
	public int getCurrentValue(){
		return faceValue;
	}

}
