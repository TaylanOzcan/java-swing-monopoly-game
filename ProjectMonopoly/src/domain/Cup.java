package domain;

public class Cup {

	public static RegularDie regDie1 = new RegularDie();
	public static RegularDie regDie2 = new RegularDie();
	public static SpeedDie speedDie = new SpeedDie();
	private static String SpeedAction = null;

	// returns the total face values of 2 regular and 1 speed dice
	public static int getTotalValue() {
		int total = 0;
		total += regDie1.getValue();
		total += regDie2.getValue();

		if(speedDie.getValue() > 0){
			total += speedDie.getCurrentValue();
		}
		else{
			SpeedAction = speedDie.DoSpecial();
		}
		return total;
	}

	// rolls and returns the total face value of 2 regular dice
	public static int getRegularValue(){
		int total = 0;
		total += regDie1.getValue();
		total += regDie2.getValue();
		return total;
	}
	
	public String getSpeedAction() {
		return SpeedAction;
	}
}
