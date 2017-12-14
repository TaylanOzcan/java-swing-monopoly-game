package domain;

public class Pool {
	private static int balance = 0;
	
	public static void payPool (Player p, int amount) {
		// REQUIRES : The pool to be created, and the player to have enough money to pay the pool.
		// MODIFIES : amount,Balance
		// EFFECTS : Pays the pool the required price.
		balance = balance + amount;
		p.increaseBalance(-amount);
	}
	
	public static void getFromPool (Player p, int amount) {
		balance = balance - amount;
		p.increaseBalance(amount);
	}
	
	public static int getBalance() {
		return balance;
	}
}
