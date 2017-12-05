package domain;

public class Pool {
private int Balance ;
public Pool (int Balance) {
	this.Balance=Balance;
}
public void PayPool (int amount) {
	this.Balance=Balance+amount;
}
public int GetFromPool () {
	return Balance;
}
}
