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
	private ActionHandler actionHandler;
	private ArrayList<PropertyListener> propertyListeners;

	public Board(ArrayList<String> playerNames){
		this.numOfPlayers = playerNames.size();
		this.players = new ArrayList<Player>(numOfPlayers);
		this.moveHandler = new MoveHandler();
		this.actionHandler = new ActionHandler();
		createPlayers(playerNames);
		this.currentPlayerIndex = 0;
		this.currentPlayer = players.get(0);
		this.propertyListeners = new ArrayList<PropertyListener>();
	}

	public void addPropertyListener(PropertyListener pl) {
		propertyListeners.add(pl);
	}

	public void publishPropertyEvent(String name, Object value) {
		for(PropertyListener pl: propertyListeners) {
			pl.onPropertyEvent(this, name, value);
		}
	}

	/**
	 * @requires: Naught
	 * @modifies:  List of players 
	 * @effects : adds players to the array of players 
	 */
	public void createPlayers(ArrayList<String> playerNames){
		for(int i=0; i<numOfPlayers; i++){
			players.add(i, new Player(i, playerNames.get(i)));
		}
	}
	/**
	 * @requires: Nothing
	 * @modifies: CurrentPlayer 
	 * @effects : Sets the new Current Player
	 */
	public void setNewCurrentPlayer(){
		if(currentPlayer.isBankrupted()) {
			computeNextPlayer();
		}else if(currentPlayer.getRolledBus()) {
			actionHandler.busAction(currentPlayer);
			return;
		}else if(currentPlayer.getRolledMonopoly()) {
			actionHandler.mrMonopolyAction(currentPlayer);
			return;
		}else if(currentPlayer.rollsAgain()){
			actionHandler.rollsAgainAction(currentPlayer);
			return;
		}else {
			computeNextPlayer();
		}
	}

	public void computeNextPlayer() {
		currentPlayerIndex++;
		currentPlayer = players.get(currentPlayerIndex % numOfPlayers);
		if(currentPlayer.isBankrupted()) {
			setNewCurrentPlayer();
		}else if(currentPlayer.getLandedOnSubway()) {
			actionHandler.landedOnSubwayAction(currentPlayer);
		}
	}

	public int getNumOfPlayers () {
		return this.numOfPlayers;
	}

	public MoveHandler getBoardMoveHandler() {
		return this.moveHandler;
	}
	public ActionHandler getActionHandler() {
		return this.actionHandler;
	}
	/**
	 * @requires: Nothing
	 * @modifies: Nothing
	 * @effects : Returns the current Player  
	 */
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	/**
	 * @requires: Nothing
	 * @modifies: player location 
	 * @effects :delegates the move handler to move the player
	 */
	public void rollDiceAndMove() {
		moveHandler.movePlayer(currentPlayer);
	}
	/**
	 * @requires: Nothing
	 * @modifies: player balance
	 * @effects : returns a boolean of whether the player bought or not 	
	 */
	public boolean buy() {
		return currentPlayer.buy();
	}
	/**
	 * @requires: Nothing
	 * @modifies: Nothing
	 * @effects :  returns the array of Players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Player> getPlayingPlayers() {
		ArrayList<Player> playingPlayers = new ArrayList<Player>(numOfPlayers);
		for(Player p: players) {
			if(!p.isBankrupted()) {
				playingPlayers.add(p);
			}
		}
		return playingPlayers;
	}

	public void mortgage() {
		//to be implemented
	}
	/**
	 * @requires: Nothing
	 * @modifies: squareToBuildHouse
	 * @effects : it returns the street index that we want to build a street on
	 */

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
	/**
	 * @requires: the street to be buyable (not a chance/community...)
	 * @modifies: Nothing
	 * @effects : returns whether the requirements is true  
	 */
	public void endTurn() {
		Cup.clearFaceValues();
		checkIfGameOver();
		setNewCurrentPlayer();
		//actionHandler.newTurnAction(currentPlayer);
	}

	public void checkIfGameOver() {
		boolean thereIsAPlayer = false;
		Player playingPlayer = null;
		for(Player p: players) {
			if(!p.isBankrupted()) {
				if(thereIsAPlayer) {
					return;
				}
				else{
					playingPlayer = p;
					thereIsAPlayer = true;
				}
			}
		}
		if(thereIsAPlayer) {
			publishPropertyEvent("gameOver", playingPlayer);
		}
	}

	/**
	 * @requires: the street to be buyable (not a chance/community...)
	 * @modifies: Nothing
	 * @effects : returns whether the requirements is true  
	 */
	public boolean isBuyable() {
		if(PropertySquare.class.isAssignableFrom(SquareFactory.getInstance().getSquare(currentPlayer.getLocation()).getClass())){
			return !((PropertySquare)SquareFactory.getInstance().getSquare(currentPlayer.getLocation())).isOwned();
		}else{
			return false;
		}
	}
	/**
	 * @requires: the current street the player is on is a property street 
	 * @modifies: Nothing
	 * @effects:  it returns whether the player can build on it or not 
	 */
	public boolean isBuildable() {
		if(SquareFactory.getInstance().getSquare(currentPlayer.getLocation()).getClass() == StreetSquare.class){
			return ((StreetSquare)SquareFactory.getInstance().getSquare(currentPlayer.getLocation())).isBuildable();
		}else{
			return false;
		}
	}
	/**
	 * @requires:Nothing.
	 * @modifies: Infolist  .
	 * @effects: Put the player information in the player arraylist
	 */
	public ArrayList<String> getPlayerInfo() {
		ArrayList<String> infoList = new ArrayList<String>(numOfPlayers);
		for(int i = 0; i < players.size(); i++){
			Player p = players.get(i);
			String info = "<html><center>"
					+ "<span style='font-size:18px'>$" + p.getBalance() + "</span><br><br>"
					+ "<span style='font-size:10px'>";
			for(PropertySquare s : p.getOwnedSquares()){
				info += s.getName() + "<br>";
			}
			info += "</span></center></html>";
			infoList.add(info);
		}
		return infoList;
	}

	public boolean repOk() {
		if(players.size() < 2 || players.size() > 8 || !players.contains(currentPlayer)
				|| currentPlayerIndex < 0 || currentPlayerIndex > players.size() - 1 || numOfPlayers < 2
				|| numOfPlayers > 8 || moveHandler == null || actionHandler == null){
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "Board [players=" + players + ", currentPlayer=" + currentPlayer + ", currentPlayerIndex="
				+ currentPlayerIndex + ", numOfPlayers=" + numOfPlayers + ", moveHandler=" + moveHandler
				+ ", actionHandler=" + actionHandler + "]";
	}

	public void moveTo(int squareIndex) {
		currentPlayer.setLocation(squareIndex);
		currentPlayer.getSquareAction();
	}

	public void moveToNearestUnownedStreetSquare(Player p) {
		ArrayList<Square> squares = SquareFactory.getInstance().getUnownedStreetSquares();
		moveHandler.moveToNearestUnownedStreetSquare(p, squares);
	}

}
