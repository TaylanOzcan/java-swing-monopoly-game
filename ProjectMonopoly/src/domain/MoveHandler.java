package domain;

import java.io.Serializable;

public class MoveHandler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void movePlayer(Player p) {
		// REQUIRES : The player to have been created,the player mustn't be in jail.
		// MODIFIES : p,fvt,newLoc,newSquare
		// EFFECTS : Changes the players location.
		if(p.isInJail()) {
			int totalRegFaceValue = Cup.rollRegularDice();
			if(rolledDoubles()) {
				p.setInJail(false);
				p.move(totalRegFaceValue);
			}
		}else {
			int totalFaceValue = Cup.rollAllDice(); //gets total face value from 2 reg and 1 speed die
			int newLoc = p.move(totalFaceValue); //gets the new location of player

			if(newLoc < totalFaceValue){
				p.increaseBalance(200); // add 200$ when player goes through the Go Square
			}

			Square newSquare = SquareFactory.getInstance().getSquare(newLoc);
			newSquare.getAction(p);

			int speedVal = Cup.speedDie.getCurrentValue();
			if(speedVal < 1) {
				if(speedVal > -2) { // Mr. Monopoly
					p.setRollsAgain(true);
				}else { // Bus icon
					// to be implemented
				}
			}

			if(rolledTriples()) {
				p.setInJail(true);
				p.setLocation(JailSquare.JAIL_LOCATION);
			}else if(rolledDoubles()) {
				p.setRollsAgain(true);
			}
		}
	}

	public boolean rolledTriples() {
		return rolledDoubles() && (Cup.regDie1.getCurrentValue() == Cup.speedDie.getCurrentValue());
	}

	public boolean rolledDoubles() {
		return Cup.regDie1.getCurrentValue() == Cup.regDie2.getCurrentValue();
	}

}