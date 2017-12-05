package domain;
import java.util.ArrayList;

public class CardDeck {
	private ArrayList<String> Chance;
	private ArrayList<String> CommunityChest;

	private ArrayList<String> TravelVouchers;
	private static int Chancecounter=-1;
	private static int Communitycounter=-1;
	private static int Vouchercounter=-1;	
	private ArrayList<String> TravelVoucher;
	public CardDeck() {
		//Adding Chance Cards To the deck 

		Chance.add("Advance to the Pay Corner");
		Chance.add("Advance to the Nearest Railroad");
		Chance.add("Advance to Illinois Ave.");
		Chance.add("Get Out of Jail Free!");
		Chance.add("Make General Repairs to all your properties.");
		Chance.add("Traffic Ticket!");
		Chance.add("Buyer’s Market!");
		Chance.add("Business Trip");
		Chance.add("Excellent Accounting");
		Chance.add("Property Taxes");
		Chance.add("Advance to Squeeze Play");
		Chance.add("Get Taken for a Ride");
		Chance.add("Pay Back!");
		Chance.add("MARDI GRAS!");
		Chance.add("Changing Lanes");
		Chance.add("Changing Lanes");

		//CommunityChest Cards Data 
		CommunityChest.add("Insurance Premiums Due");
		CommunityChest.add("Happy Birthday!");
		CommunityChest.add("Business Trip");
		CommunityChest.add("Entrepreneur of the Year!");
		CommunityChest.add("You’re getting Married");
		CommunityChest.add("Elected District Attorney");
		CommunityChest.add("BARGAIN BUSINESS!");
		CommunityChest.add("Be Kind, Rewind");
		CommunityChest.add("Get Out of Jail Free!");
		CommunityChest.add("Bank Error in Your Favor!");
		CommunityChest.add("You Win 2nd Place in an Board Game Remix Design Contest!");
		//TravelVouchers Cards Data 
		TravelVouchers.add("TRANSIT TOKEN");
		TravelVouchers.add("BUS TICKET");
		TravelVouchers.add("FREE CAB FARE MOVE BACK 3 SPACES");
		TravelVouchers.add("FREE CAB FARE MOVE AHEAD 3 SPACES");
	}
	public String DrawCard(String type) {
		if (type == "Chance") {
			Chancecounter +=1;
			return Chance.get(Chancecounter);
			}
		if (type == "CommunityChest") {
			Communitycounter +=1;
			return CommunityChest.get(Communitycounter);
			}
		if (type == "TravelVoucher") {
			Vouchercounter +=1;
			return TravelVouchers.get(Vouchercounter);
			}	
		return "Not True Coding";	

		//Adding ComunityChest Cards to the deck
		
	}

}
