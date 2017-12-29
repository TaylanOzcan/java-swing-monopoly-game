package domain;

public class VoucherSquare extends CardDrawSquare{

	private int id;
	private String name;
	
	public VoucherSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		String voucher = drawCard();
		p.addVoucher(voucher);
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
	 * @requires:The player to land on a Card Draw Square.
	 * @modifies:Nothing.
	 * @effects:Gives a player a card.
	 */
	@Override
	public String drawCard() {
		return CardDeck.getInstance().drawVoucher();
	}
}