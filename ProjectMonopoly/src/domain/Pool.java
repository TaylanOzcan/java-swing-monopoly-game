package domain;

public class Pool {
private int Balance ;
public Pool (int Balance) {
	this.Balance=Balance;
}
public void PayPool (int amount) {
	// REQUIRES : The pool to be created, and the player to have enough money to pay the pool.
	// MODIFIES : amount,Balance
	// EFFECTS : Pays the pool the required price.
	this.Balance=Balance+amount;
}
public int GetFromPool () {
	return Balance;
}
}
