package domain;

public class MoneyGiverSquare implements Square {

	private int amountToGive;
	private int id;
	private String name;
	
	public MoneyGiverSquare(int id, String name, int amountToGive){
		this.id = id;
		this.name = name;
		this.amountToGive = amountToGive;
	}
	
	@Override
	public void getAction(Player p) {
		p.EditBalance("Increase", amountToGive);
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
