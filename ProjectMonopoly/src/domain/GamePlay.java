package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class GamePlay implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Board board;
	
	public Board getBoard() {
		return board;
	}

	public void playGame(ArrayList<String> playerNames){
		board = new Board(playerNames);
	}

	public void rollDiceAndMove(){
		board.rollDiceAndMove();
	}

	public boolean buy(){
		return board.buy();
	}

	public	void mortgage(){
		board.mortgage();
	}

	public int buildHouse(int squareIndex){
		return board.buildHouse(squareIndex);
	}

	public void squeeze(){
		board.squeeze();
	}

	public void trade(int p2Index, int square1Index, int square2Index){
		board.trade(p2Index, square1Index, square2Index);
	}

	public void sell(int squareIndex){
		board.sell(squareIndex);
	}

	public void endTurn() {
		board.endTurn();
	}
	/**
	 * @requires:Nothing.
	 * @modifies:squareNames.
	 * @effects: adds the name of the owned squares to squareNames.
	 */
	public ArrayList<String> getOwnedSquareNames() {
		ArrayList<PropertySquare> squares = board.getCurrentPlayer().getOwnedSquares();
		ArrayList<String> squareNames = new ArrayList<String>(squares.size());
		for(PropertySquare s: squares){
			squareNames.add(s.getName());
		}
		return squareNames;
	}
	/**
	 * @requires:Nothing.
	 * @modifies: positions.
	 * @effects: adds the location of player to positions.
	 */
	public ArrayList<Integer> getPlayerPositions() {
		ArrayList<Integer> positions = new ArrayList<Integer>(board.getPlayers().size());
		for(Player p: board.getPlayers()){
			positions.add(p.getLocation());
		}
		return positions;
	}

	public boolean isBuyable() {
		return board.isBuyable();
	}

	public ArrayList<Player> getPlayers() {
		return board.getPlayers();
	}

	public ArrayList<String> getPlayerInfo() {
		return board.getPlayerInfo();
	}

	public boolean isBuildable() {
		return board.isBuildable();
	}

	public String getCurrentPlayerName() {
		return board.getCurrentPlayer().getName();
	}

	public int[] getDiceValues() {
		int[] values = new int[3];
		values[0] = Cup.regDie1.getCurrentValue();
		values[1] = Cup.regDie2.getCurrentValue();
		values[2] = Cup.speedDie.getCurrentValue();
		return values;
	}

	public void endAuction(Player winner, int highestBid, StreetSquare auctedSquare) {
		board.getActionHandler().endAuction(winner, highestBid, auctedSquare);
	}

	public void startAuction(StreetSquare s) {
		board.getActionHandler().startAuction(s);
	}
	
	public Square getSquare(int i) {
		return SquareFactory.getInstance().getSquare(i);
	}

	public ArrayList<Square> getUnownedStreetSquares() {
		return SquareFactory.getInstance().getUnownedStreetSquares();
	}

	public void checkAuction() {
		board.getActionHandler().checkAuction(board.getCurrentPlayer());
	}

	public Player getCurrentPlayer() {
		return board.getCurrentPlayer();
	}

}