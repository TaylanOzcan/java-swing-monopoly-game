package domain;

import java.io.Serializable;

public class TaxSquare implements Square{

	private int id;
	private String name;
	
	public TaxSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		// TODO Auto-generated method stub
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
