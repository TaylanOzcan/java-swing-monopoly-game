package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int JAIL_LOCATION = 10;
	
	private String name;
	private int location;
	private int balance;
	private boolean CurrentPlayer;
	private ArrayList<String> vouchers;
	private ArrayList<String> chanceCards;
	private ArrayList<String> communityCards;
	private boolean inJail;
	private boolean rollsAgain;
	private boolean rolledMonopoly;
	private boolean rolledBus;
	private boolean rolledDoubles;
	private int consecutiveDoublesCount;
	
	private boolean reverseDirection;
	private ArrayList<PropertySquare> ownedSquares;


	public Player(String name) {
		this.name = name;
		this.location = 0;
		this.balance = 3200;
		this.inJail = false;
		this.rollsAgain = false;
		this.reverseDirection = false;
		this.vouchers = new ArrayList<String>(5);
		this.chanceCards = new ArrayList<String>(5);
		this.communityCards = new ArrayList<String>(5);	
		this.ownedSquares = new ArrayList<PropertySquare>(10);
	}
	
	public boolean getRolledBus() {
		return rolledBus;
	}

	public void setRolledBus(boolean rolledBus) {
		this.rolledBus = rolledBus;
	}

	public boolean getRolledMonopoly() {
		return rolledMonopoly;
	}

	public void setRolledMonopoly(boolean rolledMonopoly) {
		this.rolledMonopoly = rolledMonopoly;
	}

	public void rolledDoubles() {
		if(rolledDoubles) {
			consecutiveDoublesCount++;
		}
		setRolledDoubles(true);
	}

	public void setRolledDoubles(boolean rolledDoubles) {
		this.rolledDoubles = rolledDoubles;
		if(rolledDoubles==false) consecutiveDoublesCount = 0;
	}

	public int getConsecutiveDoublesCount() {
		return consecutiveDoublesCount;
	}

	public boolean isReverseDirection() {
		return reverseDirection;
	}

	public void setReverseDirection(boolean reverseDirection) {
		this.reverseDirection = reverseDirection;
	}

	public boolean IsCurrent() {
		return this.CurrentPlayer;
	}
	
	
	public boolean isInJail() {
		return this.inJail;
	}
	public void goIntoJail() {
		this.inJail = true;
		this.setLocation(JAIL_LOCATION);
	}
	public void getOutOfJail() {
		this.inJail = false;
	}
	
	public boolean rollsAgain() {
		return this.rollsAgain;
	}
	public void setRollsAgain(boolean rollsAgain) {
		this.rollsAgain = rollsAgain;
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

	public void payRent(int rentPrice, Player renter){
		// REQUIRES : this.balance must be greater than rentPrice
		// MODIFIES : this.balance
		// EFFECTS : subtracts rentprice from this.balance
		//and returns modified balance.
		this.increaseBalance(-rentPrice);
		renter.increaseBalance(rentPrice);
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
		this.location = (this.location + rollValue); // % 40;
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
		return buy(squareToBuy);
	}
	
	public boolean buy(PropertySquare squareToBuy) {
		if(this.balance < squareToBuy.getPrice()){
			return false;
		}else{
			return buyFor(squareToBuy, squareToBuy.getPrice());
		}
	}
	
	public boolean buyFor(PropertySquare squareToBuy, int price) {
		this.pay(price);
		this.addOwnedSquare(squareToBuy);
		return true;
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
				+ communityCards + ", inJail=" + inJail + ", ownedSquares=" + ownedSquares + "]";
	}

}
