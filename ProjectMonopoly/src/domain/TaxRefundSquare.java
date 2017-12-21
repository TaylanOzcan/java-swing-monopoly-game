package domain;

import java.io.Serializable;

public class TaxRefundSquare implements Square{

	private int id;
	private String name;
	
	public TaxRefundSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	/**
	 * @requires:The player to land on Tax Return Square.
	 * @modifies:Player's and Pool's balance.
	 * @effects:Decreases from Pool's balance and adds that amount to Player's balance.
	 */
	@Override
	public void getAction(Player p) {
		Pool.getFromPool(p, Pool.getBalance() / 2);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getId() {
		return id;
	}

}
