package domain;

public class Cup {

	public static RegularDie regDie1 = new RegularDie();
	public static RegularDie regDie2 = new RegularDie();
	public static SpeedDie speedDie = new SpeedDie();

	/**
	 * @requires:Nothing.
	 * @modifies:Die value.
	 * @effects:Returns the value of dice roll total.
	 */
	public static int rollAllDice() {
		int total = 0;
		total += regDie1.roll();
		total += regDie2.roll();
		int speedVal = speedDie.roll();

		if(speedVal > 0){ // Pips (1,2 or 3)
			total += speedVal;
		}
		return total;
	}
	
	/**
	 * @requires:Nothing.
	 * @modifies: total .
	 * @effects  returns the total face value of 2 regular dice
	 */
	public static int rollRegularDice(){
		int total = 0;
		total += regDie1.roll();
		total += regDie2.roll();
		return total;
	}
	
	public static int getSpeedValue() {
		return speedDie.getCurrentValue();
	}
	/**
	 * @requires:Nothing.
	 * @modifies: regDie 1 2 and speedDie .
	 * @effects  sets the given die values to -3.
	 */
	public static void clearFaceValues() {
		regDie1.setCurrentValue(-3);
		regDie2.setCurrentValue(-3);
		speedDie.setCurrentValue(-3);
	}
	public int getRegDie1Value() {
		return regDie1.getCurrentValue();
	}
	public int getRegDie2Value() {
		return regDie2.getCurrentValue();
	}
	
	public boolean repOk() {
		if(regDie1==null || regDie2==null || speedDie==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "Cup [One random instance of rolling: RegDie1: " + regDie1.roll() + 
				" ,RegDie2: "+ regDie2.roll() + " ,speedDie: " + speedDie.roll() + "]";
	}

}
