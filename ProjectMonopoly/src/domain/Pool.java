package domain;

public class Pool {
	private static int balance = 0;
	/**
	 * @requires:The pool to be created, and the player to have enough money to pay the pool.
	 * @modifies: amount,Balance
	 * @effects:Pays the pool the required price.
	 */
	public static void payPool (Player p, int amount) {
	
		balance = balance + amount;
		p.increaseBalance(-amount);
	}
	/**
	 * @requires:Nothing.
	 * @modifies:Player and pool balance.
	 * @effects:Decreases from Pool's balance and adds that amount to Player's balance.
	 */
	public static void getFromPool (Player p, int amount) {
		balance = balance - amount;
		p.increaseBalance(amount);
	}
	
	public static int getBalance() {
		return balance;
	}
}
