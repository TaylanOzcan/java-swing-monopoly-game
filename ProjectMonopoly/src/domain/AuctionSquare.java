package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class AuctionSquare implements Square{
	
	private int id;
	private String name;
	private ArrayList<PropertyListener> propertyListeners;
	
	public AuctionSquare(int id, String name){
		this.id = id;
		this.name = name;
		this.propertyListeners = new ArrayList<PropertyListener>();
	}
	
	@Override
	public void getAction(Player p) {
		publishPropertyEvent("auctionDialog", p);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public void addPropertyListener(PropertyListener pl) {
		propertyListeners.add(pl);
	}
	
	public void publishPropertyEvent(String name, Object value) {
		for(PropertyListener pl: propertyListeners) {
			pl.onPropertyEvent(this, name, value);
		}
	}
	public String getColor() {
		return null;
	}
}
