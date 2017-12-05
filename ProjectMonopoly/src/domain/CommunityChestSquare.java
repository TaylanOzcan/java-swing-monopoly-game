package domain;
public class CommunityChestSquare extends CardDrawSquare {

	private int id;
	private String name;
	
	public CommunityChestSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getId() {
		return this.id;
	}

}
