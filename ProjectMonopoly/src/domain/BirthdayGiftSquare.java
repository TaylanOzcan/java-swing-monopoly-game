package domain;

import java.io.Serializable;

public class BirthdayGiftSquare implements Square{
	
	private int id;
	private String name;
	
	public BirthdayGiftSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		// notify gui to show selection panel (100$ or voucher)
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
