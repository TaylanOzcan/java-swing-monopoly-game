package domain;
public abstract class PropertySquare implements Square {
	
	int id;
	String name;
	int price;
	int rent;
	Player owner;
	boolean isOwned ;
	boolean isMortgage;
	
	public boolean isOwned(){
		return isOwned;
	}
	
	public void setOwner(Player newowner){
		this.owner = newowner;
		this.isOwned = true;
	}
	
	public void deleteOwner(){
		// REQUIRES : An owner.
		// MODIFIES : isOwned,owner.
		// EFFECTS : Deletes an owner.
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
				+ owner + ", isOwned=" + isOwned + ", isMortgage=" + isMortgage + "]";
	}
}
