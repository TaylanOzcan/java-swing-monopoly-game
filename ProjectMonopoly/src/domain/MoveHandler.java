package domain;

import java.io.Serializable;

public class MoveHandler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @requires:The player to have been created,the player mustn't be in jail.
	 * @modifies:p,fvt,newLoc,newSquare
	 * @effects:Changes the players location.
	 */
	public void movePlayer(Player p) {
		int initLoc = p.getLocation();
		int finalLoc;

		if(p.getRolledMonopoly()) {
			p.setRolledMonopoly(false);
			return;
		}else if(p.getRolledBus()) {
			p.setRolledBus(false);
			return;
		}else if(p.isInJail()) {
			int totalRegFaceValue = Cup.rollRegularDice();
			if(doublesRolled()) {
				p.getOutOfJail();
				finalLoc = computeLoc(initLoc, totalRegFaceValue, false);
				p.setLocation(finalLoc);
			}
		}else {
			int totalFaceValue = Cup.rollAllDice(); //gets total face value from 2 reg and 1 speed die

			if(triplesRolled()) {
				// player goes to any square
				p.setRolledDoubles(false);
				return;
			}else if(doublesRolled()) {
				p.rolledDoubles();
				if(p.getConsecutiveDoublesCount()==3) {
					p.goIntoJail();
					p.setRolledDoubles(false);
					return;
				}
				p.setRollsAgain(true);
			}else {
				p.setRolledDoubles(false);
			}

			finalLoc = computeLoc(initLoc, totalFaceValue, p.isReverseDirection());

			if(finalLoc>=40 && finalLoc<55 && ((initLoc<40 && initLoc>24)
					|| (initLoc<62 && initLoc>51 &&totalFaceValue%2==0))) {
				finalLoc -= 40;
				p.increaseBalance(200);
			}else if(finalLoc>=120) {
				finalLoc -= 56;
			}else if(finalLoc>=64 && finalLoc<79 && ((initLoc<64 && initLoc>48)
					|| (initLoc<36 && initLoc>25 &&totalFaceValue%2==0))) {
				finalLoc -= 24;
			}

			p.setReverseDirection(false);
			p.setLocation(finalLoc);
			p.getSquareAction();

			int speedVal = Cup.speedDie.getCurrentValue();
			if(speedVal < 1) {
				if(speedVal > -2) { // Mr. Monopoly
					p.setRolledMonopoly(true);
				}else { // Bus icon
					p.setRolledBus(true);
				}
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

	private boolean triplesRolled() {
		return doublesRolled() && (Cup.regDie1.getCurrentValue() == Cup.speedDie.getCurrentValue());
	}

	private boolean doublesRolled() {
		return Cup.regDie1.getCurrentValue() == Cup.regDie2.getCurrentValue();
	}

}