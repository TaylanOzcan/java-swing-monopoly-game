package domain;
public class ChanceSquare extends CardDrawSquare {

	private int id;
	private String name;
	
	public ChanceSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		String chanceCard = drawCard();
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

	@Override
	public String drawCard() {
		return CardDeck.drawchanceCard();
	}
}
