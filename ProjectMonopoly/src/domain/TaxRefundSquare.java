package domain;

public class TaxRefundSquare implements Square{

	private int id;
	private String name;
	
	public TaxRefundSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
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
