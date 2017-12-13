package domain;
 
public class GoToJailSquare implements Square {

	private int id;
	private String name;
	
	public GoToJailSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		p.goIntoJail();
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
