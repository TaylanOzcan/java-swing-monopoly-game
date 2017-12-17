package domain;
import java.util.Random;

public class SpeedDie implements Die {

	private int faceValue;
	Random rnd;
	
	public SpeedDie(){
		this.rnd = new Random();
	}

	

	public int getValue() { //comments above
		
	return faceValue;
	}
	/**
	 * @requires:The Die.
	 * @modifies:The value of the die.
	 * @effects: returns an integer in range [-2,3] --- (1, 2, 3 are normal values and -2, -1, 0 are specials such as mr. monopoly and bus
	 */
	public int roll() {
		
		faceValue = rnd.nextInt(6) - 2;
		return faceValue;
	}
	/**
	 * @requires:The Die.
	 * @modifies:Nothing.
	 * @effects:Returns either Mr.Monopoly or Bus Icon depending on the face value.
	 */
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
		if(faceValue < -2 || faceValue > 3) {
			return false;
		}return true;
	}

	@Override
	public String toString() {
		return "SpeedDie [faceValue=" + faceValue + ", rnd=" + rnd + "]";
	}

	public void setCurrentValue(int i) {
		this.faceValue = i;
	}
	
}
