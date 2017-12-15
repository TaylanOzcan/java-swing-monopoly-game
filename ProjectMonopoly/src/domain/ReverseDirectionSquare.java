package domain;

public class ReverseDirectionSquare implements Square {

	private int id;
	private String name;
	
	public ReverseDirectionSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		p.setReverseDirection(true);
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
