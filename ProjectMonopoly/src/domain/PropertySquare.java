package domain;

import java.io.Serializable;

public abstract class PropertySquare implements Square, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id;
	String name;
	int price;
	int rent;
	Player owner;
	boolean isOwned;
	
	public PropertySquare() {
		this.isOwned = false;
		this.owner = null;
	}
	
	public boolean isOwned(){
		return isOwned;
	}
	
	public void setOwner(Player newowner){
		this.owner = newowner;
		this.isOwned = true;
	}
	/**
	 * @requires:An owner to have been assigned to a square.
	 * @modifies:isOwned and the owner.
	 * @effects:Deletes the owner from a property.
	 */
	public void deleteOwner(){
		this.owner = null;
		this.isOwned = false;
	}
	
	public int getPrice(){
		return this.price;
	}

	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}

	

	@Override
	public String toString() {
		return "PropertySquare [id=" + id + ", name=" + name + ", price=" + price + ", rent=" + rent + ", owner="
				+ owner + ", isOwned=" + isOwned + "]";
	}
}
