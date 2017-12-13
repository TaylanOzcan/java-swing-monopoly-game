package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int location;
	private int balance;
	private boolean CurrentPlayer;
	private ArrayList<String> vouchers;
	private ArrayList<String> chanceCards;
	private ArrayList<String> communityCards;
	private boolean InJail;
	private ArrayList<PropertySquare> ownedSquares;


	public Player(String name) {
		this.name = name;
		this.location = 0;
		this.balance = 3200;
		this.vouchers = new ArrayList<String>(5);
		this.chanceCards = new ArrayList<String>(5);
		this.communityCards = new ArrayList<String>(5);	
		this.ownedSquares = new ArrayList<PropertySquare>(10);
	}

	public boolean IsCurrent() {
		return this.CurrentPlayer;
	}
	
	public void setCurrent() {
		this.CurrentPlayer=CurrentPlayer;
	}
	
	public boolean IsInJail() {
		return this.InJail;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void addVoucher(String newVoucher) {
		this.vouchers.add(newVoucher);
	}
	
	public void deleteVoucher(String voucher){
		if(this.vouchers.contains(voucher)){
			this.vouchers.remove(voucher);
		}else{
			//give an error
		}
	}
	
	public ArrayList<PropertySquare> getOwnedSquares() {
		return ownedSquares;
	}

	public void payRent(int rentPrice){
		// REQUIRES : this.balance must be greater than rentPrice
		// MODIFIES : this.balance
		// EFFECTS : subtracts rentprice from this.balance
		//and returns modified balance.
		setBalance(balance-rentPrice) ;
	}

	public void addChanceCard(String newCard) {
		// MODIFIES : this.chanceCards
		// EFFECTS : add newCard to this.chanceCards
		this.chanceCards.add(newCard);
	}
	public void deleteChanceCard(String card){
		// REQUIRES : this.chanceCards contains card
		// EFFECTS : removes card from this.chanceCards
		if(this.chanceCards.contains(card)){
			this.chanceCards.remove(card);}
		}

	public void EditBalance(String Type,int money){
		if (Type=="Increase") {
		int b = balance + money;
		setBalance(b);}
		if (Type=="Decrease") {
			int b = balance - money;
			setBalance(b);}
	}
	
	public void addCommunityCard(String newCard) {
		// MODIFIES : this.communityCards
		// EFFECTS : add newCard to this.communityCards
		this.communityCards.add(newCard);
	}
	public void deleteCommunityCard(String card){
		// REQUIRES : this.communityCards contains card
		// EFFECTS : removes card from this.communityCards
		if(this.communityCards.contains(card)){
			this.communityCards.remove(card);
		}else{
			//give an error
		}
	}

	public int move(int rollValue) {
		// MODIFIES : this.location
		// EFFECTS : returns modified location
		this.location = (this.location + rollValue) % 40;
		return this.location;
	}

	public void pay(int price) {
		// REQUIRES : this.balance is greater than price
		// MODIFIES : this.balance
		// EFFECTS : subtracts price from this.balance
		this.balance -= price;
	}

	public void increaseBalance(int moneyToAdd) {
		// MODIFIES : this.balance
		// EFFECTS : adds moneyToAdd to this.balance
		this.balance += moneyToAdd;
	}

	public boolean buy() {
		// REQUIRES : squareToBuy is in PropertySquare type
		// MODIFIES : this.balance, this.ownedSquare
		// EFFECTS : decreases this.balance by squareToBuy.getPrice()
		// and adds squareToBuy to this.ownedSquares
		PropertySquare squareToBuy = (PropertySquare)SquareFactory.getInstance().getSquare(this.location);
		if(this.balance < squareToBuy.getPrice()){
			return false;
		}else{
			this.pay(squareToBuy.getPrice());
			this.addOwnedSquare(squareToBuy);
			return true;
		}
	}
	
	public ArrayList<String> getVouchers() {
		return vouchers;
	}

	public ArrayList<String> getChanceCards() {
		return chanceCards;
	}

	public ArrayList<String> getCommunityCards() {
		return communityCards;
	}

	public void addOwnedSquare(PropertySquare squareToBuy){
		// MODIFIES : squareToBuy
		// EFFECTS : adds squareToBuy to this.ownedSquares
		squareToBuy.setOwner(this);
		this.ownedSquares.add(squareToBuy);
	}
	
	public boolean repOk() {
		if(this.name == null || this.location<0 || this.location > 120) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", location=" + location + ", balance=" + balance + ", CurrentPlayer="
				+ CurrentPlayer + ", vouchers=" + vouchers + ", chanceCards=" + chanceCards + ", communityCards="
				+ communityCards + ", InJail=" + InJail + ", ownedSquares=" + ownedSquares + "]";
	}

}
