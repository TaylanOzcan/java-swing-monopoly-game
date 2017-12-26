package domain;

import java.io.Serializable;

public class CommunityChestSquare extends CardDrawSquare{

	private int id;
	private String name;
	
	public CommunityChestSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		String communityCard = drawCard();
		if (communityCard == "Get Out of Jail Free!") {
			p.addCommunityCard("Get Out of Jail Free!");
		}
		if (communityCard == "Bank Error in Your Favor!") {
			p.EditBalance("Increase", 200);
		}
		if (communityCard == "You Win 2nd Place in an Board Game Remix Design Contest!") {
			p.EditBalance("Increase",10);
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getId() {
		return this.id;
	}
	/**
	 * @requires:Nothing.
	 * @modifies:Nothing.
	 * @effects:Draw card.
	 */
	@Override
	public String drawCard() {
		return CardDeck.getInstance().drawCommunityCard();
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
