package domain;

import java.io.Serializable;

public class BonusSquare implements Square{

	private int id;
	private String name;
	private final int AMOUNT_TO_GIVE = 50;
	
	public BonusSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	/**
	 * @requires:Player to land on bonus square.
	 * @modifies:Player balance.
	 * @effects:Player is given money.
	 */
	@Override
	public void getAction(Player p) {
		p.increaseBalance(AMOUNT_TO_GIVE);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getId() {
		return id;
	}
	public String getColor() {
		return null;
	}
}
