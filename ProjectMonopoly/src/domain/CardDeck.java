package domain;
import java.util.Collections;
import java.util.LinkedList;

public class CardDeck{

	private LinkedList<String> chanceCards;
	private LinkedList<String> communityChestCards;
	private LinkedList<String> travelVouchers;
	
	private static CardDeck instance;
	
	private CardDeck() {
		chanceCards = new LinkedList<String>();
		communityChestCards = new LinkedList<String>();
		travelVouchers = new LinkedList<String>();
		
		//Adding chanceCards Cards To the deck 
		//chanceCards.add("Advance to the Pay Corner");
		//chanceCards.add("Advance to the Nearest Railroad");
		chanceCards.add("Hurricane");
		chanceCards.add("Get Out of Jail Free!");
		//chanceCards.add("Make General Repairs to All Your Properties");
		//chanceCards.add("Traffic Ticket!");
		//chanceCards.add("Buyer's Market!");
		//chanceCards.add("Business Trip");
		//chanceCards.add("Excellent Accounting");
		//chanceCards.add("Property Taxes");
		//chanceCards.add("Advance to Squeeze Play");
		//chanceCards.add("Get Taken for a Ride");
		//chanceCards.add("Pay Back!");
		//chanceCards.add("MARDI GRAS!");
		//chanceCards.add("Changing Lanes");
		//chanceCards.add("Changing Lanes");
		chanceCards.add("Go Back (3) Spaces");
		chanceCards.add("School Fees");
		chanceCards.add("Advance to Illinois Ave.");
		//communityChestCards Cards Data 
		communityChestCards.add("Get Out of Jail Free!");
		communityChestCards.add("Go To Jail!");
		communityChestCards.add("Business Trip");
		communityChestCards.add("Tech Bubble Bursts");
		
		//travelVouchers Cards Data 
		travelVouchers.add("TRANSIT TOKEN");
		travelVouchers.add("BUS TICKET");
		travelVouchers.add("FREE CAB FARE MOVE BACK 3 SPACES");
		travelVouchers.add("FREE CAB FARE MOVE AHEAD 3 SPACES");
		
		Collections.shuffle(chanceCards);
		Collections.shuffle(communityChestCards);
		Collections.shuffle(travelVouchers);
	}

	public static CardDeck getInstance() {
		if(instance == null) instance = new CardDeck();
		return instance;
	}
	
	public String drawChanceCard() {
		return chanceCards.remove();
	}
	
	public String drawCommunityCard() {
		return communityChestCards.remove();
	}
	
	public String drawVoucher() {
		return travelVouchers.get((int) (Math.random()*4));
	}

}
