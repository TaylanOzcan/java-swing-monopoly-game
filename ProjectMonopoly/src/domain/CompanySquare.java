package domain;

public class CompanySquare extends PropertySquare{

	@Override
	public void getAction(Player p) {
		//pay rent if owned by another player
		//notify gui to activate buy button if not owned
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
