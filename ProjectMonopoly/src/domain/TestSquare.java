package domain;

import java.io.Serializable;

public class TestSquare implements Square{

	private int id;
	private String name;
	
	public TestSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		//System.out.println("test square " + id + "\n");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
