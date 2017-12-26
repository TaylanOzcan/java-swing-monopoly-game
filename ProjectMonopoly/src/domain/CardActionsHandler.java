package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class CardActionsHandler{

private Square square;
private ArrayList<Player> players;
private String CurrentCard;
private Player cp;

public CardActionsHandler (ArrayList<Player> players,Square square) {
	this.players=players;
	this.square=square;
	for(int i=0;i< players.size();i++) {
		if(players.get(i).IsCurrent())
			cp=players.get(i);
	}
}



}
