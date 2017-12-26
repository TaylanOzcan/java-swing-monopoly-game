package domain;

import java.io.Serializable;

public class RegularSquare implements Square{

	private int id;
	private String name;
	
	public RegularSquare(int id, String name){
		this.id = id;
		this.name = name;
	}

	@Override
	public void getAction(Player p) {
		
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
