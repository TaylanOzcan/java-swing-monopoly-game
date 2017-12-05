package domain;

import java.io.Serializable;

public class MoveHandler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MoveHandler(){
	}

	public void movePlayer(Player p) {
		int fvt = Cup.getTotalValue(); //gets total face value from 2 reg and 1 speed die
		int newLoc = p.move(fvt); //gets the new location of player
		if(newLoc < fvt){
			p.increaseBalance(200); // add 200$ when player goes through the Go Square
		}
		Square newSquare = SquareFactory.getSquare(newLoc);
		newSquare.getAction(p);
	}
}