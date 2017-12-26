package domain;

import java.io.Serializable;

public class SubwaySquare implements Square{

	private int id;
	private String name;
	
	public SubwaySquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		p.setLandedOnSubway(true);
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
