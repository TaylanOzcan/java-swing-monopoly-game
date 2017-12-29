package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class StreetSquare extends PropertySquare implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int house1Price;
	private int house2Price;
	private int house3Price;
	private int house4Price;
	private int hotelPrice;
	private int skyscraperPrice;
	private int nHouses;
	private int nHotels;
	private int nSkyscrapers;
	private String color;
	private boolean isBuildable;
	private boolean isMortgaged;
	private boolean hasBuilding;
	private ArrayList<PropertyListener> propertyListeners;

	public StreetSquare(int id, String name, int house1Price, int house2Price, int house3Price, 
			int house4Price, int hotelPrice, int skyscraperPrice, int rent, int price, String color){
		this.id = id;
		this.name = name;
		this.price = price;
		this.rent = rent;
		this.house1Price = house1Price;
		this.house2Price = house2Price;
		this.house3Price = house3Price;
		this.house4Price = house4Price;
		this.hotelPrice = hotelPrice;
		this.skyscraperPrice = skyscraperPrice;
		this.color = color;
		this.isBuildable = true;
		this.isMortgaged = false;
		this.hasBuilding = false;
		this.propertyListeners = new ArrayList<PropertyListener>();
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
	 * @requires:The player to land on a Street square owned by another player.
	 * @modifies:Player's balance.
	 * @effects:Decreases Player's balance by the rent amount of the square he is placed on(if that square is owned by another player).
	 */
	@Override
	public void getAction(Player p) {
		if(isOwned()) {
			if(owner != p) {
				p.payRent(this);
			}
		}else {
			// notify gui to activate buy button if not owned
		}
	}


	/**
	 * @requires:The player to own the square he wants to build on.
	 * @modifies:The property of the square.
	 * @effects:Adds either a hotel,a house or a skyscraper to the square.
	 */
	public void build(){
		int result = this.id;
		if(nSkyscrapers == 1){
			result = -1;
		}else if(nHotels == 1){
			if(owner.getBalance()<skyscraperPrice) {
				result = -2;
			}else {
				owner.pay(this.skyscraperPrice);
				nHotels = 0;
				nSkyscrapers = 1;
				isBuildable = false;
			}
		}else if(nHouses==4){
			if(owner.getBalance()<hotelPrice) {
				result = -2;
			}else {
				owner.pay(this.hotelPrice);
				nHouses = 0;
				nHotels = 1;
			}	
		}else if(nHouses==3){
			if(owner.getBalance()<house4Price) {
				result = -2;
			}else {
				owner.pay(this.house4Price);
				nHouses++;
			}
		}else if(nHouses==2){
			if(owner.getBalance()<house3Price) {
				result = -2;
			}else {
				owner.pay(this.house3Price);
				nHouses++;
			}
		}else if(nHouses==1){
			if(owner.getBalance()<house2Price) {
				result = -2;
			}else {
				owner.pay(this.house2Price);
				nHouses++;
			}
		}else if(nHouses==0){
			if(owner.getBalance()<house1Price) {
				result = -2;
			}else {
				owner.pay(this.house1Price);
				nHouses++;
				hasBuilding = true;
			}
		}
		publishPropertyEvent("build", result);
	}

	public void demolish(){
		int result = this.id;
		if(nSkyscrapers == 1){
			nHotels = 1;
			nSkyscrapers = 0;
			isBuildable = true;
		}else if(nHotels == 1){
			nHotels = 0;
			nHouses = 4;
		}else if(nHouses==4){
			nHouses--;
		}else if(nHouses==3){
			nHouses--;
		}else if(nHouses==2){
			nHouses--;
		}else if(nHouses==1) {
			nHouses--;
			hasBuilding = false;
		}else if(nHouses==0){
			result = -1;
		}
		publishPropertyEvent("demolish", result);
	}

	public int getSkyscraperPrice() {
		return skyscraperPrice;
	}
	public int getHotelPrice() {
		return hotelPrice;
	}

	public int getnHouses() {
		return nHouses;
	}
	public int getnHotels() {
		return nHotels;
	}
	public int getnSkyscrapers() {
		return nSkyscrapers;
	}

	public String getColor() {
		return color;
	}

	public boolean isBuildable() {
		return isBuildable;
	}

	public void setBuildable(boolean isBuildable) {
		this.isBuildable = isBuildable;
	}
	public boolean repOk() {
		if(this.hotelPrice <= 0 || this.house1Price <= 0 || this.house2Price <= 0 
				|| this.house3Price <= 0 || this.house4Price <= 0 || this.id < 0 
				|| this.name == null || this.price <= 0 || this.rent <= 0 || this.skyscraperPrice <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "StreetSquare [house1Price=" + house1Price + ", house2Price=" + house2Price + ", house3Price="
				+ house3Price + ", house4Price=" + house4Price + ", hotelPrice=" + hotelPrice + ", skyscraperPrice="
				+ skyscraperPrice + ", nHouses=" + nHouses + ", nHotels=" + nHotels + ", nSkyscrapers=" + nSkyscrapers
				+ ", color=" + color + ", isBuildable=" + isBuildable + ", isMortgaged=" + isMortgaged + "]";
	}

	public boolean hasBuilding() {
		return hasBuilding;
	}

}
