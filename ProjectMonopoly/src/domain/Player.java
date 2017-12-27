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
	private int id;
	private boolean CurrentPlayer;
	private ArrayList<String> vouchers;
	private ArrayList<String> chanceCards;
	private ArrayList<String> communityCards;
	private boolean inJail;
	private boolean landedOnSubway;
	private boolean rollsAgain;
	private boolean rolledMonopoly;
	private boolean rolledBus;
	private boolean rolledDoubles;
	private int consecutiveDoublesCount;
	private boolean reverseDirection;
	private boolean isBankrupted;
	private ArrayList<PropertySquare> ownedSquares;
	private ArrayList<PropertyListener> propertyListeners;
	private boolean hurricaneActivated;

	public Player(int id, String name) {
		this.name = name;
		this.location = 0;
		this.balance = 3200;
		this.id = id;
		this.inJail = false;
		this.rollsAgain = false;
		this.reverseDirection = false;
		this.isBankrupted = false;
		this.consecutiveDoublesCount = 0;
		this.vouchers = new ArrayList<String>(5);
		this.chanceCards = new ArrayList<String>(5);
		this.communityCards = new ArrayList<String>(5);	
		this.ownedSquares = new ArrayList<PropertySquare>();
		this.propertyListeners = new ArrayList<PropertyListener>();
		this.hurricaneActivated = false;
	}

	public void addPropertyListener(PropertyListener pl) {
		propertyListeners.add(pl);
	}

	public void publishPropertyEvent(String name, Object value) {
		for(PropertyListener pl: propertyListeners) {
			pl.onPropertyEvent(this, name, value);
		}
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
	/**
	 * @requires:The dice to have been rolled.
	 * @modifies:setRolledDoubles
	 * @effects:The player rolls again.
	 */
	public void rolledDoubles() {
		if(rolledDoubles) {
			consecutiveDoublesCount++;
		}else {
			consecutiveDoublesCount = 1;
		}
		setRolledDoubles(true);
	}

	public void rolledTriples() {
		rolledDoubles = false;
		publishPropertyEvent("rolledTriples", null);
	}

	public void setRolledDoubles(boolean rolledDoubles) {
		this.rolledDoubles = rolledDoubles;
		if(rolledDoubles == false) consecutiveDoublesCount = 0;
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
	/**
	 * @requires:The player to have landed on Jail Square or done an action that resulted him to be transported to Jail Square.
	 * @modifies:Player's location.
	 * @effects:Player's location has been updated as Jail Square.
	 */
	public void goIntoJail() {
		this.inJail = true;
		this.setLocation(JAIL_LOCATION);
		publishPropertyEvent("wentToJail", consecutiveDoublesCount==3);
	}
	/**
	 * @requires:The player to be in Jail.
	 * @modifies:Player's location
	 * @effects:Player is taken out of the Jail Square.
	 */
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
		//getSquareAction();
	}

	public void getSquareAction() {
		Square newSquare = SquareFactory.getInstance().getSquare(location);
		newSquare.getAction(this);
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
		if(balance<0) {
			setBankrupted(true);
			publishPropertyEvent("bankrupted", balance);
		}
	}

	public void addVoucher(String newVoucher) {
		this.vouchers.add(newVoucher);
	}
	/**
	 * @requires:The player to have a voucher.
	 * @modifies:Player's voucher amount.
	 * @effects:Delete a voucher from a player.
	 */
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
	/**
	 * @requires: this.balance must be greater than rentPrice
	 * @modifies: this.balance
	 * @effects: subtracts rent price from this.balance and returns modified balance.
	 */
	public void payRent(PropertySquare square){
		int rentAmount = square.getRent();
		Player renter = square.getOwner();
		this.increaseBalance(-rentAmount);
		renter.increaseBalance(rentAmount);
		publishPropertyEvent("rentPaid", square);
	}
	/**
	 * @requires: Nothing.
	 * @modifies: this.chanceCards
	 * @effects: add newCard to this.chanceCards
	 */
	public void addChanceCard(String newCard) {
		this.chanceCards.add(newCard);
	}
	/**
	 * @requires:  this.chanceCards contains card
	 * @modifies: Nothing.
	 * @effects: removes card from this.chanceCards
	 */
	public void deleteChanceCard(String card){
		if(this.chanceCards.contains(card)){
			this.chanceCards.remove(card);}
	}
	/**
	 * @requires:Nothing
	 * @modifies:Player's balance.
	 * @effects: Increase or Decrease player balance.
	 */
	public void EditBalance(String Type,int money){
		if (Type=="Increase") {
			int b = balance + money;
			setBalance(b);}
		if (Type=="Decrease") {
			int b = balance - money;
			setBalance(b);}
	}
	/**
	 * @requires: Nothing.
	 * @modifies: this.communityCards.
	 * @effects: add New card to this.communityCards
	 */
	public void addCommunityCard(String newCard) {
		this.communityCards.add(newCard);
	}
	/**
	 * @requires:  this.communityCards contains card
	 * @modifies: Nothing.
	 * @effects: removes card from this.communityCards
	 */
	public void deleteCommunityCard(String card){
		if(this.communityCards.contains(card)){
			this.communityCards.remove(card);
		}else{
			//give an error
		}
	}
	/**
	 * @requires: Nothing.
	 * @modifies: this.loc
	 * @effects: return modified loc
	 */
	public int move(int rollValue) {
		setLocation(this.location + rollValue);
		return this.location;
	}
	/**
	 * @requires:  this.balance is greater than price
	 * @modifies: this.balance
	 * @effects: subtracts price from this.balance
	 */
	public void pay(int price) {
		setBalance(balance - price);
	}
	/**
	 * @modifies:  this.balance
	 * @requires: Nothing.
	 * @effects: adds moneyToAdd to this.balance
	 */
	public void increaseBalance(int moneyToAdd) {
		setBalance(balance + moneyToAdd);
	}
	/**
	 * @requires:   squareToBuy is in PropertySquare type
	 * @modifies: this.balance, this.ownedSquare
	 * @effects: decreases this.balance by squareToBuy.getPrice() and adds squareToBuy to this.ownedSquares
	 */
	public boolean buy() {
		PropertySquare squareToBuy = (PropertySquare)SquareFactory.getInstance().getSquare(this.location);
		return buy(squareToBuy);
	}
	/**
	 * @requires: Nothing.
	 * @modifies: Nothing.
	 * @effects: returns true or false depending on player balance compared to square price.
	 */
	public boolean buy(PropertySquare squareToBuy) {
		if(this.balance < squareToBuy.getPrice()){
			return false;
		}else{
			return buyFor(squareToBuy, squareToBuy.getPrice());
		}
	}
	/**
	 * @requires: Buying process to have been started.
	 * @modifies: price, squareToBuy.
	 * @effects: pays the price and adds squareToBuy to owned squares.
	 */
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
	
	public void useCard(int selection, CardActionsHandler handler) {
		int cardType = selection / 100;
		if(cardType == 1) {
			handler.useCard(this, chanceCards.remove(selection % 100));
		}else if(cardType == 2) {
			handler.useCard(this, communityCards.remove(selection % 100));
		}else {
			handler.useCard(this, vouchers.remove(selection % 100));
		}
	}
	/**
	 * @requires: Nothing
	 * @modifies: squareToBuy
	 * @effects: adds squareToBuy to this.ownedSquares
	 */
	public void addOwnedSquare(PropertySquare squareToBuy){
		squareToBuy.setOwner(this);
		this.ownedSquares.add(squareToBuy);
	}

	public boolean repOk() {
		if(this.name == null || this.location<0 || this.location > 120) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", location=" + location + ", balance=" + balance + ", CurrentPlayer="
				+ CurrentPlayer + ", vouchers=" + vouchers + ", chanceCards=" + chanceCards + ", communityCards="
				+ communityCards + ", inJail=" + inJail + ", ownedSquares=" + ownedSquares + "]";
	}

	public boolean getLandedOnSubway() {
		return landedOnSubway;
	}

	public void setLandedOnSubway(boolean landedOnSubway) {
		this.landedOnSubway = landedOnSubway;
	}

	public int getId() {
		return id;
	}

	public boolean getHurricane() {
		return hurricaneActivated;
	}
	public void setHurricane(boolean t) {
		this.hurricaneActivated= t;
	}

	public boolean isBankrupted() {
		return isBankrupted;
	}

	private void setBankrupted(boolean isBankrupted) {
		this.isBankrupted = isBankrupted;
	}

}