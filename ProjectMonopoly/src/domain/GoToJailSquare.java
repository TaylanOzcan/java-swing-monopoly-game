package domain;

import java.io.Serializable;

public class GoToJailSquare implements Square{

	private int id;
	private String name;
	
	public GoToJailSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	/**
	 * @requires:Player to have done an action that would make him go to Jail.
	 * @modifies:Players location.
	 * @effects:Players location is set to Jail.
	 */
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
