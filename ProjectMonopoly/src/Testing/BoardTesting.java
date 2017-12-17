package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Board;
import domain.Cup;
import domain.Player;
import domain.PropertySquare;
import domain.SquareFactory;
import domain.StreetSquare;

public class BoardTesting {

	@Test
	public void testBoard() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		Board b = new Board(r);
		assertNotNull(b);
	}

	@Test
	public void testcreatePlayers() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		assertNotNull(tb.getPlayers());
	}

	@Test
	public void testSetNewCurrentPlayer() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		Player OldCurrentPlayer = tb.getCurrentPlayer();
		tb.setNewCurrentPlayer();
		Player NewCurrentPlayer=tb.getCurrentPlayer();
		assertNotSame(OldCurrentPlayer,NewCurrentPlayer);
	}

	@Test
	public void testRollDiceAndMove() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		int	OldPlayerLocation = tb.getCurrentPlayer().getLocation();
		tb.rollDiceAndMove();
		int NewPlayerLocation = tb.getCurrentPlayer().getLocation();
		assertNotSame (OldPlayerLocation,NewPlayerLocation);
	}

	@Test
	public void testBuildHouse() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().addOwnedSquare((PropertySquare) SquareFactory.getInstance().getSquare(18));
		StreetSquare Square = (StreetSquare)tb.getCurrentPlayer().getOwnedSquares().get(0);
		int NumberOfHousesBeforeBuild = Square.getnHouses();
		tb.buildHouse(0);
		int NumberOfHousesAfterBuild = Square.getnHouses();
		assertNotSame (NumberOfHousesBeforeBuild,NumberOfHousesAfterBuild); //assert they increasd 
		assertSame (NumberOfHousesBeforeBuild,NumberOfHousesAfterBuild-1);//assert housed has increased by one
	}
	@Test 
	public void testNumberofHousesBuild() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().addOwnedSquare((PropertySquare) SquareFactory.getInstance().getSquare(18));
		StreetSquare Square = (StreetSquare)tb.getCurrentPlayer().getOwnedSquares().get(0);
		int NumberOfHousesBeforeBuild = Square.getnHouses();
		tb.buildHouse(0);
		int NumberOfHousesAfterBuild = Square.getnHouses();
		assertSame (NumberOfHousesBeforeBuild,NumberOfHousesAfterBuild-1);//assert housed has increased by one
	}

	@Test
	public void testIsntBuyable() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().setLocation(18);
		tb.getCurrentPlayer().addOwnedSquare((PropertySquare) SquareFactory.getInstance().getSquare(tb.getCurrentPlayer().getLocation()));
		StreetSquare Square = (StreetSquare)tb.getCurrentPlayer().getOwnedSquares().get(0);
		assertFalse(tb.isBuyable());
	}
	@Test
	public void testIsBuyable() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().setLocation(108);
		assertTrue (tb.isBuyable());
	}

	@Test
	public void testIsBuildable() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2"); 
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().setLocation(24);
		assertTrue(tb.isBuildable());
	}
	@Test
	public void testIsntBuildable() {
		ArrayList<String> r = new ArrayList<String>();
		r.add("test1");
		r.add("test2"); 
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().setLocation(4);
		assertFalse(tb.isBuildable());
	}


}
