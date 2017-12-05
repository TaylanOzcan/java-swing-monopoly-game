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

	public void payRent(int rentprice){
		setBalance(balance-rentprice) ;
	}

	public void addChanceCard(String newCard) {
		this.chanceCards.add(newCard);
	}
	public void deleteChanceCard(String card){
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
		this.communityCards.add(newCard);
	}
	public void deleteCommunityCard(String card){
		if(this.communityCards.contains(card)){
			this.communityCards.remove(card);
		}else{
			//give an error
		}
	}

	public int move(int rollValue) {
		this.location = (this.location + rollValue) % 40;
		return this.location;
	}

	public void pay(int price) {
		this.balance -= price;
	}

	public void increaseBalance(int moneyToAdd) {
		this.balance += moneyToAdd;
	}

	public boolean buy() {
		PropertySquare squareToBuy = (PropertySquare)SquareFactory.getSquare(this.location);
		if(this.balance < squareToBuy.getPrice()){
			return false;
		}else{
			this.pay(squareToBuy.getPrice());
			this.addOwnedSquare(squareToBuy);
			return true;
		}
	}
	
	public void addOwnedSquare(PropertySquare squareToBuy){
		squareToBuy.setOwner(this);
		this.ownedSquares.add(squareToBuy);
	}

}
