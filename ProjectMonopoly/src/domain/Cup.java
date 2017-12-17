package domain;

public class Cup {

	public static RegularDie regDie1 = new RegularDie();
	public static RegularDie regDie2 = new RegularDie();
	public static SpeedDie speedDie = new SpeedDie();

	// returns the total face values of 2 regular and 1 speed dice
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

	// rolls and returns the total face value of 2 regular dice
	public static int rollRegularDice(){
		int total = 0;
		total += regDie1.roll();
		total += regDie2.roll();
		return total;
	}
	
	public static int getSpeedValue() {
		return speedDie.getCurrentValue();
	}
	
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
	

}
