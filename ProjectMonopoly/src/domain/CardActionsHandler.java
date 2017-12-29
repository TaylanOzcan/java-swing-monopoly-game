package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class CardActionsHandler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private ArrayList<PropertyListener> propertyListeners;

	public CardActionsHandler() {
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
	
	public void useCard(Player p, String card) {
		if(card.equals("Hurricane")) {
			publishPropertyEvent("hurricane", p);
		}
	}

}
