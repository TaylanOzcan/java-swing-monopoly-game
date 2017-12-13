package domain;
import java.util.ArrayList;

public class CardActionsHandler {
private Square square;
private ArrayList<Player>  players;
private String CurrentCard;
private Pool pool;
private Player cp;

public CardActionsHandler (ArrayList<Player> players,Square square, Pool pool) {
	this.players=players;
	this.square=square;
	this.pool=pool;
	for(int i=0;i< players.size();i++) {
		if(players.get(i).IsCurrent())
			cp=players.get(i);
	}
}
public void DrawACard (String Type) {
	if (Type == "Community") {
		this.CurrentCard=CardDeck.getInstance().drawCommunityCard();
		if (CurrentCard == "Insurance Premiums Due") {
			pool.PayPool(50);
		}
		if (CurrentCard == "Happy Birthday!") {
			for (int i=0 ;i< players.size() ;i++) {
				if (players.get(i).IsCurrent() == true)
					players.get(i).EditBalance("Increase", 10);
				players.get(i).EditBalance("Decrease",10);
			}
			
		}
		if (CurrentCard == "Business Trip") {
			cp.addVoucher(CardDeck.getInstance().drawVoucher());
			cp.addVoucher(CardDeck.getInstance().drawVoucher());
		}
		if (CurrentCard == "Entrepreneur of the Year!") {
			for (int i=0 ;i< players.size() ;i++) {
				if (players.get(i).IsCurrent() == true)
					players.get(i).EditBalance("Increase", 50);
				players.get(i).EditBalance("Decrease",50);
			}
			
		}
		if (CurrentCard == "You’re getting Married") {
			for (int i=0 ;i< players.size() ;i++) {
				if (players.get(i).IsCurrent() == true)
					players.get(i).EditBalance("Increase", 25);
				players.get(i).EditBalance("Decrease",25);
			}
		}
		if (CurrentCard == "Elected District Attorney") {
			
		}
		if (CurrentCard == "BARGAIN BUSINESS!") {
			cp.addCommunityCard("BARGAIN BUSINESS!");
		}
		if (CurrentCard == "Be Kind, Rewind") {
			
		}
		if (CurrentCard == "Get Out of Jail Free!") {
			cp.addCommunityCard("Get Out of Jail Free!");
		}
		if (CurrentCard == "Bank Error in Your Favor!") {
			cp.EditBalance("Increase", 200);
		}
		if (CurrentCard == "You Win 2nd Place in an Board Game Remix Design Contest!") {
			cp.EditBalance("Increase",10);
		}
	}
}
}
