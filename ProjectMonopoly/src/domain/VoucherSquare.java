package domain;
public class VoucherSquare extends CardDrawSquare {

	private int id;
	private String name;
	
	public VoucherSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		String voucher = drawCard();
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
		return CardDeck.drawVoucher();
	}
}