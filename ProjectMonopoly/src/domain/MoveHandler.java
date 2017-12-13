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
		int fvt = Cup.getTotalValue(); //gets total face value from 2 reg and 1 speed die
		int newLoc = p.move(fvt); //gets the new location of player
		if(newLoc < fvt){
			p.increaseBalance(200); // add 200$ when player goes through the Go Square
		}
		Square newSquare = SquareFactory.getInstance().getSquare(newLoc);
		newSquare.getAction(p);
	}
}