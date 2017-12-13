package domain;
import java.util.ArrayList;

public class CardDeck {
	private static ArrayList<String> chanceCards;
	private static ArrayList<String> communityChestCards;
	private static ArrayList<String> travelVouchers;
	
	private static int chanceCardscounter=-1;
	private static int Communitycounter=-1;
	private static int Vouchercounter=-1;
	
	public CardDeck() {
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
	public String DrawCard(String type) {
		if (type == "chanceCards") {
			chanceCardscounter +=1;
			return chanceCards.get(chanceCardscounter);
			}
		if (type == "communityChestCards") {
			Communitycounter +=1;
			return communityChestCards.get(Communitycounter);
			}
		if (type == "TravelVoucher") {
			Vouchercounter +=1;
			return travelVouchers.get(Vouchercounter);
			}	
		return "Not True Coding";	
	}
	
	public static String drawchanceCard() {
		return chanceCards.remove(0);
	}
	
	public static String drawCommunityCard() {
		return communityChestCards.remove(0);
	}
	
	public static String drawVoucher() {
		return travelVouchers.remove(0);
	}

}
