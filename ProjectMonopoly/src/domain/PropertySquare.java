package domain;

import java.io.Serializable;

public abstract class PropertySquare implements Square, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int id;
	protected String name;
	protected int price;
	protected int rent;
	protected Player owner;
	protected boolean isOwned;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getRent() {
		return rent;
	}

	public Player getOwner() {
		return owner;
	}

	public PropertySquare() {
		this.isOwned = false;
		this.owner = null;
	}
	
	public boolean isOwned(){
		return isOwned;
	}
	
	public void setOwner(Player newOwner){
		this.owner = newOwner;
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
