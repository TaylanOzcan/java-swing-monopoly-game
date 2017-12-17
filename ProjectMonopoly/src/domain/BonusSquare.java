package domain;

public class BonusSquare implements Square {

	private int id;
	private String name;
	private final int AMOUNT_TO_GIVE = 50;
	
	public BonusSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
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

}
