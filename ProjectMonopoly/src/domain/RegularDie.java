package domain;
import java.util.Random;

public class RegularDie implements Die {
	
	private int faceValue;
	Random rnd;
	
	public RegularDie(){
		this.rnd = new Random();
	}
	/**
	 * @requires:The Die.
	 * @modifies:The Die's face value.
	 * @effects:Returns the die's face value.
	 */
	@Override
	public int roll() {
		faceValue = rnd.nextInt(6) + 1;
		return faceValue;
	}
	
	public int getCurrentValue(){
		return faceValue;
	}
	
	public boolean repOk() {
		if (faceValue > 6 || faceValue < 1) {
			return false;
		}return true;
	}

	@Override
	public String toString() {
		return "RegularDie [faceValue=" + faceValue + ", rnd=" + rnd + "]";
	}
	
	public void setCurrentValue(int i) {
		this.faceValue = i;
	}

}
