package domain;

import java.util.ArrayList;

public class ActionHandler {

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
	
	public void startAuction(StreetSquare s) {
		publishPropertyEvent("startAuction", s);
	}
	
	public void endAuction(Player winner, int highestBid, StreetSquare auctedSquare) {
		winner.buyFor(auctedSquare, highestBid);
		//System.out.println(winner + " bought " + auctedSquare.getName() + " for " + highestBid + " dollars.");
	}

	public void checkAuction(Player currentPlayer) {
		Square currentSquare = SquareFactory.getInstance().getSquare(currentPlayer.getLocation());
		if(currentSquare.getClass() == StreetSquare.class){
			startAuction((StreetSquare)currentSquare);
		}
	}
}
