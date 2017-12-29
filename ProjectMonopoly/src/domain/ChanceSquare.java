package domain;

public class ChanceSquare extends CardDrawSquare  {

	private int id;
	private String name;

	public ChanceSquare(int id, String name){
		this.id = id;
		this.name = name;
	}

	@Override
	public void getAction(Player p) {
		String currentCard = drawCard();
		p.addChanceCard(currentCard);
		/*
			if (currentCard == "Get Out of Jail Free!") {
				p.addChanceCard("Get Out of Jail Free!");
			}
			if (currentCard == "Advance to the Stock Exchange") {
				p.setLocation(52);
			}
			if (currentCard == "Traffic Ticket!") {
				p.EditBalance("decrease",15);
			}
			if (currentCard == "Hurricane Card") {
				p.setHurricane(true);
			}
		 */
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
		return CardDeck.getInstance().drawChanceCard();
	}
}
