package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class ActionHandler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<PropertyListener> propertyListeners;

	public ActionHandler() {
		propertyListeners = new ArrayList<PropertyListener>();
	}

	public void addPropertyListener(PropertyListener pl) {
		propertyListeners.add(pl);
	}

	public void publishPropertyEvent(String name, Object value) {
		for(PropertyListener pl: propertyListeners) {
			pl.onPropertyEvent(this, name, value);
		}
	}
	/**
	 * @requires:A player to pass by a square without buying it.
	 * @modifies:Nothing.
	 * @effects:State of the game.
	 */
	public void startAuction(StreetSquare s) {
		publishPropertyEvent("startAuction", s);
	}
	/**
	 * @requires:An auction to have been started.
	 * @modifies:The property of a Street Square.
	 * @effects:Ends the auction.
	 */
	public void endAuction(Player winner, int highestBid, StreetSquare auctedSquare) {
		winner.buyFor(auctedSquare, highestBid);
	}
	/**
	 * @requires:
	 * @modifies:Nothing.
	 * @effects:Starts auction if currentPlayer is on a StreetSquare
	 */
	public void checkAuction(Player currentPlayer) {
		Square currentSquare = SquareFactory.getInstance().getSquare(currentPlayer.getLocation());
		if(currentSquare.getClass() == StreetSquare.class){
			startAuction((StreetSquare)currentSquare);
		}
	}

	public void mrMonopolyAction(Player currentPlayer) {
		// send the player to nearest unowned square
		publishPropertyEvent("mrMonopolyAction", currentPlayer/*may be changed with the square to be sent*/);
	}

	public void busAction(Player currentPlayer) {
		String voucher = CardDeck.getInstance().drawVoucher();
		publishPropertyEvent("busAction", currentPlayer/*may be changed with the voucher given*/);
		currentPlayer.addVoucher(voucher);
	}

	public void rollsAgainAction(Player currentPlayer) {
		currentPlayer.setRollsAgain(false);
		publishPropertyEvent("rollsAgainAction", currentPlayer);
	}

	public void newTurnAction(Player currentPlayer) {
		publishPropertyEvent("newTurnAction", currentPlayer);
	}

	public void landedOnSubwayAction(Player currentPlayer) {
		currentPlayer.setLandedOnSubway(false);
		publishPropertyEvent("landedOnSubwayAction", currentPlayer);
	}
}