package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class CardActionsHandler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private ArrayList<PropertyListener> propertyListeners;
	private static CardActionsHandler instance;

	private CardActionsHandler() {
		propertyListeners = new ArrayList<PropertyListener>();
	}
	
	public static CardActionsHandler getInstance() {
		if(instance == null) instance = new CardActionsHandler();
		return instance;
	}	

	public void addPropertyListener(PropertyListener pl) {
		propertyListeners.add(pl);
	}

	public void publishPropertyEvent(String name, Object value) {
		for(PropertyListener pl: propertyListeners) {
			pl.onPropertyEvent(this, name, value);
		}
	}

	public void useCard(Player p, String card) {
		if(card.equals("Hurricane")) {
			publishPropertyEvent("hurricane", p);
		}else if (card.equals("Get Out of Jail Free!")) {
			p.getOutOfJail();
		}else if (card.equals("Go To Jail!")) {
			p.goIntoJail();
		}else if (card.equals("Go Back (3) Spaces")){
			p.move(-3);
		}else if (card.equals("School Fees")) {
			Pool.payPool(p, 150);
		}else if (card.equals("Advance to Illinois Ave.")) {
			p.setLocation(24);
		}else if (card.equals("Business Trip")) {
			p.addVoucher(CardDeck.getInstance().drawVoucher());
			p.addVoucher(CardDeck.getInstance().drawVoucher());
		}else if (card.equals("Tech Bubble Bursts")) {
			Pool.payPool(p, 150);
		}	
	}
}


