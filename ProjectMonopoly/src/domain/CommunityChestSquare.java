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
		String communityCard = drawCard();
		//CardActionHandler will handle the action
		//not yet implemented
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

}
