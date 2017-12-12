package domain;
import java.util.Random;

public class SpeedDie implements Die {

	private int faceValue;
	Random rnd;
	
	public SpeedDie(){
		this.rnd = new Random();
	}

	@Override
	public int getValue() {
		//REQUIRES: The Die.
		//EFFECTS: returns an integer in range [-2,3] --- (1, 2, 3 are normal values and -2, -1, 0 are specials such as mr. monopoly and bus)
		faceValue = rnd.nextInt(6) - 2;
		return faceValue;
	}
	public String DoSpecial() {
	if(faceValue == -1 || faceValue == 0)
		return "Mr Monopoly";
	if (faceValue== -2)
		return "Bus Icon";
	return null;
	}
	
	public int getCurrentValue(){
		return faceValue;
	}
	public boolean repOk() {
		if(faceValue < -2 || faceValue>3) {
			return false;
		}return true;
	}

	@Override
	public String toString() {
		return "SpeedDie [faceValue=" + faceValue + ", rnd=" + rnd + "]";
	}
	
}
