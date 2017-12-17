package domain;
import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
	private Queue<String> chanceCards;
	private Queue<String> communityChestCards;
	private Queue<String> travelVouchers;
	
	private static CardDeck instance;
	
	private CardDeck() {
		chanceCards = new LinkedList<String>();
		communityChestCards = new LinkedList<String>();
		travelVouchers = new LinkedList<String>();
		
		//Adding chanceCards Cards To the deck 
		chanceCards.add("Advance to the Pay Corner");
		chanceCards.add("Advance to the Nearest Railroad");
		chanceCards.add("Advance to Illinois Ave.");
		chanceCards.add("Get Out of Jail Free!");
		chanceCards.add("Make General Repairs to all your properties.");
		chanceCards.add("Traffic Ticket!");
		chanceCards.add("Buyer’s Market!");
		chanceCards.add("Business Trip");
		chanceCards.add("Excellent Accounting");
		chanceCards.add("Property Taxes");
		chanceCards.add("Advance to Squeeze Play");
		chanceCards.add("Get Taken for a Ride");
		chanceCards.add("Pay Back!");
		chanceCards.add("MARDI GRAS!");
		chanceCards.add("Changing Lanes");
		chanceCards.add("Changing Lanes");

		//communityChestCards Cards Data 
		communityChestCards.add("Insurance Premiums Due");
		communityChestCards.add("Happy Birthday!");
		communityChestCards.add("Business Trip");
		communityChestCards.add("Entrepreneur of the Year!");
		communityChestCards.add("You’re getting Married");
		communityChestCards.add("Elected District Attorney");
		communityChestCards.add("BARGAIN BUSINESS!");
		communityChestCards.add("Be Kind, Rewind");
		communityChestCards.add("Get Out of Jail Free!");
		communityChestCards.add("Bank Error in Your Favor!");
		communityChestCards.add("You Win 2nd Place in an Board Game Remix Design Contest!");
		
		//travelVouchers Cards Data 
		travelVouchers.add("TRANSIT TOKEN");
		travelVouchers.add("BUS TICKET");
		travelVouchers.add("FREE CAB FARE MOVE BACK 3 SPACES");
		travelVouchers.add("FREE CAB FARE MOVE AHEAD 3 SPACES");
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
		return travelVouchers.remove();
	}

}
