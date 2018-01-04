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
		this.propertyListeners = new ArrayList<PropertyListener>(1);
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
		this.setRolledMonopoly(false);
		this.setRolledBus(false);
		this.setRolledDoubles(false);
		this.setRollsAgain(false);
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
		publishPropertyEvent("chanceCardAdded", newCard);
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
	 * @requires: Nothing.
	 * @modifies: this.communityCards.
	 * @effects: add New card to this.communityCards
	 */
	public void addCommunityCard(String newCard) {
		this.communityCards.add(newCard);
		publishPropertyEvent("communityCardAdded", newCard);
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
	
	public void addVoucher(String newVoucher) {
		this.vouchers.add(newVoucher);
		publishPropertyEvent("voucherAdded", newVoucher);
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

	public void sell(int squareIndex, Player buyer, int price) {
		PropertySquare ps = ownedSquares.get(squareIndex);
		this.increaseBalance(price);
		this.removeOwnedSquare(ps);
		buyer.pay(price);
		buyer.addOwnedSquare(ps);
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
	
	public void useCard(int selection) {
		int cardType = selection / 100;
		if(cardType == 1) {
			useChanceCard(selection % 100);
		}else if(cardType == 2) {
			useCommunityCard(selection % 100);
		}else {
			useVoucher(selection % 100);
		}
	}
	
	public void useVoucher(int index) {
		CardActionsHandler.getInstance().useCard(this, vouchers.remove(index));
	}
	public void useCommunityCard(int index) {
		CardActionsHandler.getInstance().useCard(this, communityCards.remove(index));
	}
	public void useChanceCard(int index) {
		CardActionsHandler.getInstance().useCard(this, chanceCards.remove(index));
	}
	/**
	 * @requires: Nothing
	 * @modifies: squareToBuy
	 * @effects: adds squareToBuy to this.ownedSquares
	 */
	public void addOwnedSquare(PropertySquare squareToAdd){
		squareToAdd.setOwner(this);
		this.ownedSquares.add(squareToAdd);
	}
	
	public void removeOwnedSquare(PropertySquare squareToRemove) {
		squareToRemove.deleteOwner();
		this.ownedSquares.remove(squareToRemove);
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
		return "Player [name=" + name + ", location=" + location + ", balance=" + balance 
				+ ", vouchers=" + vouchers + ", chanceCards=" + chanceCards + ", communityCards="
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