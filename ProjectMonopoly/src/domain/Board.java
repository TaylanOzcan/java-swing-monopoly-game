package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable{
	//OverView : This class is an information expert that knows about Players
	//and Creates them as well 
	private static final long serialVersionUID = 1L;

	private ArrayList<Player> players;
	private Player currentPlayer;
	private int currentPlayerIndex;
	private int numOfPlayers;
	private MoveHandler moveHandler;

	//Effects: It effects the creation process of players as the number is taken 
	//from the playerNames size ,Makes new instance of the move handler, and makes
	//the current player as the first player in the newly created list of Players 
	//as well as delegating SquareFactory to createSquares.

	public Board(ArrayList<String> playerNames){
		this.numOfPlayers = playerNames.size();
		this.players = new ArrayList<Player>(numOfPlayers);
		this.moveHandler = new MoveHandler();
		createPlayers(playerNames);
		this.currentPlayerIndex = 0;
		this.currentPlayer = players.get(0);
	}

	//Effects: adds players to the array of players 
	//Modifies : List of players 
	public void createPlayers(ArrayList<String> playerNames){
		for(int i=0; i<numOfPlayers; i++){
			players.add(i, new Player(playerNames.get(i)));
		}
	}
	//Effects : Sets the new Current Player 
	//Modifies : CurrentPlayer to become the next player
	public void setNewCurrentPlayer(){
		if(currentPlayer.getRolledBus()) {
			return;
		}else if(currentPlayer.getRolledMonopoly()) {
			return;
		}else if(currentPlayer.rollsAgain()){
			currentPlayer.setRollsAgain(false);
		}else {
			currentPlayerIndex++;
			currentPlayer = players.get(currentPlayerIndex % numOfPlayers);
		}
	}

	public int GetNumOfPlayers () {
		return this.numOfPlayers;
	}
	public MoveHandler getBoardMoveHandler() {
		return this.moveHandler;
	}

	//Effects : Returns the current Player
	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}
	//Effects : delegates the move handler to move the player
	public void rollDiceAndMove() {
		moveHandler.movePlayer(currentPlayer);
	}
	//Effects : returns a boolean of whether the player bought or not 	 
	public boolean buy() {
		return currentPlayer.buy();
	}
	//Effects : returns the array of Players
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void mortgage() {
		//to be implemented
	}
	//Effects : it returns the street index that we want to build a street on
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
		Cup.clearFaceValues();
		setNewCurrentPlayer();
	}
	//Requiers : the street to be buyable (not a chance/community...) square
	//THE STREET isn't owned 
	//effects : returns whether the requirements is true 
	public boolean isBuyable() {
		if(PropertySquare.class.isAssignableFrom(SquareFactory.getInstance().getSquare(currentPlayer.getLocation()).getClass())){
			return !((PropertySquare)SquareFactory.getInstance().getSquare(currentPlayer.getLocation())).isOwned();
		}else{
			return false;
		}
	}
	//Requiers : the current street the player is on is a property street 
	//Effects : it returns whether the player can build on it or not 
	public boolean isBuildable() {
		if(SquareFactory.getInstance().getSquare(currentPlayer.getLocation()).getClass() == StreetSquare.class){
			return !((StreetSquare)SquareFactory.getInstance().getSquare(currentPlayer.getLocation())).isBuildable();
		}else{
			return false;
		}
	}
	//Effects : Put the player information in the player arraylist 
	//Modifies : Infolist 
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
