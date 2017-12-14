package domain;
import java.awt.Color;
import java.io.Serializable;

public class StreetSquare extends PropertySquare implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int house1Price;
	int house2Price;
	int house3Price;
	int house4Price;
	int hotelPrice;
	int skyscraperPrice;
	int nHouses;
	int nHotels;
	int nSkyscrapers;
	Color color;
	boolean isBuildable;
	boolean isMortgaged;

	public StreetSquare(int id, String name, int house1Price, int house2Price, int house3Price, 
			int house4Price, int hotelPrice, int skyscraperPrice, int rent, int price, Color color){
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
	}

	@Override
	public void getAction(Player p) {
		// pay rent if owned by another player
		// notify gui to activate buy button if not owned
	}

	public int build(){
		// REQUIRES : An unowned Square,the player to have the required Balance. 
		// MODIFIES : IS THIS JUST THE INPUT?
		// EFFECTS : Builds a house if there isn't anything,a hotel if there are 5 houses,a skyscraper if there is a hotel on the square.
		if(nSkyscrapers == 1){
			return -1;
		}else if(nHotels == 1){
			owner.pay(this.skyscraperPrice);
			nHotels = 0;
			nSkyscrapers = 1;
			isBuildable = false;
		}else if(nHouses==4){
			owner.pay(this.hotelPrice);
			nHouses = 0;
			nHotels = 1;
		}else if(nHouses==3){
			owner.pay(this.house4Price);
			nHouses++;
		}else if(nHouses==2){
			owner.pay(this.house3Price);
			nHouses++;
		}else if(nHouses==1){
			owner.pay(this.house2Price);
			nHouses++;
		}else if(nHouses==0){
			owner.pay(this.house1Price);
			nHouses++;
		}
		return this.id;
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

	public Color getColor() {
		return color;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getId() {
		return id;
	}

	public boolean isBuildable() {
		return isBuildable;
	}

	public void setBuildable(boolean isBuildable) {
		this.isBuildable = isBuildable;
	}
	public boolean repOk() {
		if(this.hotelPrice <= 0 || this.house1Price <= 0 || this.house2Price <= 0 || this.house3Price <= 0 || this.house4Price <= 0 || this.id < 0 || this.name == null || this.price <= 0 || this.rent <= 0 || this.skyscraperPrice <= 0) {
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

}
