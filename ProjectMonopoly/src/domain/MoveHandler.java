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
		int initLoc = p.getLocation();
		int finalLoc;

		if(p.isInJail()) {
			int totalRegFaceValue = Cup.rollRegularDice();
			if(rolledDoubles()) {
				p.getOutOfJail();
				finalLoc = computeLoc(initLoc, totalRegFaceValue, false);
				p.setLocation(finalLoc);
			}
		}else {
			int totalFaceValue = Cup.rollAllDice(); //gets total face value from 2 reg and 1 speed die
			finalLoc = computeLoc(initLoc, totalFaceValue, p.isReverseDirection());

			if(finalLoc>=40 && finalLoc<55 && ((initLoc<40 && initLoc>24) || (initLoc<62 && initLoc>51 &&totalFaceValue%2==0))) {
				finalLoc -= 40;
				p.increaseBalance(200);
			}else if(finalLoc>=120) {
				finalLoc -= 56;
			}else if(finalLoc>=64 && finalLoc<79 && ((initLoc<64 && initLoc>48) || (initLoc<36 && initLoc>25 &&totalFaceValue%2==0))) {
				finalLoc -= 24;
			}

			p.setLocation(finalLoc);
			p.setReverseDirection(false);

			Square newSquare = SquareFactory.getInstance().getSquare(finalLoc);
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
				p.goIntoJail();
			}else if(rolledDoubles()) {
				p.setRollsAgain(true);
			}
		}
	}

	private int computeLoc(int initLoc, int stepsToMove, boolean isReverse) {
		int finalLoc = initLoc + (isReverse ? -stepsToMove : stepsToMove);

		if(stepsToMove%2 == 0) {
			if(!isReverse) {
				if(initLoc <= 5 && finalLoc > 5) {
					finalLoc += 66;
				}else if(initLoc <= 71 && finalLoc > 71) {
					finalLoc -= 66;
				}else if(initLoc <= 15 && finalLoc > 15) {
					finalLoc += 34;
				}else if(initLoc <= 49 && finalLoc > 49) {
					finalLoc -= 34;
				}else if(initLoc <= 25 && finalLoc > 25) {
					finalLoc += 74;
				}else if(initLoc <= 99 && finalLoc > 99) {
					finalLoc -= 74;
				}else if(initLoc <= 35 && finalLoc > 35) {
					finalLoc += 26;
				}else if(initLoc <= 61 && finalLoc > 61) {
					finalLoc -= 26;
				}
			}else {
				if(initLoc > 61 && finalLoc < 61) {
					finalLoc -= 26;
				}
			}
		}
		return finalLoc;
	}

	private boolean rolledTriples() {
		return rolledDoubles() && (Cup.regDie1.getCurrentValue() == Cup.speedDie.getCurrentValue());
	}

	private boolean rolledDoubles() {
		return Cup.regDie1.getCurrentValue() == Cup.regDie2.getCurrentValue();
	}

}