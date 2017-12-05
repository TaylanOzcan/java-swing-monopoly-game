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
		// TODO Auto-generated method stub

	}

	public int build(){
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
		return this.name;
	}
	@Override
	public int getId() {
		return this.id;
	}

	public boolean isBuildable() {
		return isBuildable;
	}

	public void setBuildable(boolean isBuildable) {
		this.isBuildable = isBuildable;
	}
	

}
