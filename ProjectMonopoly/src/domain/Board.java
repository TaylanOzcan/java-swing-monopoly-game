package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Player> players;
	private Player currentPlayer;
	private int currentPlayerIndex;
	private int numOfPlayers;
	private MoveHandler moveHandler;

	public Board(ArrayList<String> playerNames){
		this.numOfPlayers = playerNames.size();
		this.players = new ArrayList<Player>(numOfPlayers);
		this.moveHandler = new MoveHandler();
		createPlayers(playerNames);
		this.currentPlayerIndex = 0;
		this.currentPlayer = players.get(0);
		SquareFactory.createSquares();

	}

	public void createPlayers(ArrayList<String> playerNames){
		for(int i=0; i<numOfPlayers; i++){
			players.add(i, new Player(playerNames.get(i)));
		}
	}

	public void setNewCurrentPlayer(){
		currentPlayerIndex++;
		currentPlayer = players.get(currentPlayerIndex % numOfPlayers);
	}

	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

	public void rollDice() {
		moveHandler.movePlayer(currentPlayer);
	}

	public boolean buy() {
		return currentPlayer.buy();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void mortgage() {
		//to be implemented
	}

	public int buildHouse(int squareIndex) {
		StreetSquare squareToBuildHouse = (StreetSquare)currentPlayer.getOwnedSquares().get(squareIndex);
		return squareToBuildHouse.build();
	}

	public void squeeze() {
		// TODO Auto-generated method stub

	}

	public void trade(int p2Index, int square1Index, int square2Index) {
		// TODO Auto-generated method stub

	}

	public void sell(int squareIndex) {
		// TODO Auto-generated method stub

	}

	public void endTurn() {
		setNewCurrentPlayer();
	}

	public boolean isBuyable() {
		if(PropertySquare.class.isAssignableFrom(SquareFactory.getSquare(currentPlayer.getLocation()).getClass())){
			return !((PropertySquare)SquareFactory.getSquare(currentPlayer.getLocation())).isOwned();
		}else{
			return false;
		}
	}
	
	public boolean isBuildable() {
		if(SquareFactory.getSquare(currentPlayer.getLocation()).getClass() == StreetSquare.class){
			return !((StreetSquare)SquareFactory.getSquare(currentPlayer.getLocation())).isBuildable();
		}else{
			return false;
		}
	}

	public ArrayList<String> getPlayerInfo() {
		ArrayList<String> infoList = new ArrayList<String>(numOfPlayers);
		for(int i = 0; i < players.size(); i++){
			Player p = players.get(i);
			String info = "<html><center><span style='font-size:16px'>" + (i+1) + ": " + p.getName() + "</span>"
							+ "<br><br><span style='font-size:12px'>$ " + p.getBalance() + "</span><br><br>"
							+ "<span style='font-size:10px'>";
			for(PropertySquare s : p.getOwnedSquares()){
				info += s.getName() + "<br>";
			}
			info += "</span></center></html>";
			infoList.add(info);
		}
		return infoList;
	}
}
