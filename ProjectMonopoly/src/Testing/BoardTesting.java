package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Board;
import domain.Player;
import domain.PropertySquare;
import domain.SquareFactory;
import domain.StreetSquare;

public class BoardTesting {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testBoard() {

		ArrayList r = new ArrayList<String> ( );
		r.add("test1");
		Board b = new Board(r);
		assertNotNull(b);
	}

	@Test
	public void testcreatePlayers() {
		
		ArrayList r = new ArrayList<String> ( );
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
	assertNotNull(tb.getPlayers());
	
	}

	@Test
	public void testSetNewCurrentPlayer() {
		
		ArrayList r = new ArrayList<String> ( );
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
	public void testRollDice() {
		ArrayList r = new ArrayList<String> ( );
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		int	OldPlayerLocation = tb.getCurrentPlayer().getLocation();
		tb.rollDice();
		int NewPlayerLocation = tb.getCurrentPlayer().getLocation();
		assertNotSame (OldPlayerLocation,NewPlayerLocation);
		
	}

	@Test
		public void testBuildHouse() {
			ArrayList r = new ArrayList<String> ( );
			r.add("test1");
			r.add("test2");
			r.add("test3");
			Board tb = new Board(r);
			tb.getCurrentPlayer().addOwnedSquare((PropertySquare) SquareFactory.getSquare(18));
			StreetSquare Square = (StreetSquare)tb.getCurrentPlayer().getOwnedSquares().get(0);
		int NumberOfHousesBeforeBuild = Square.getnHouses();
		tb.buildHouse(0);
		int NumberOfHousesAfterBuild = Square.getnHouses();
		assertNotSame (NumberOfHousesBeforeBuild,NumberOfHousesAfterBuild);
	}

	@Test
	public void testIsBuyable() {
		ArrayList r = new ArrayList<String> ( );
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().setLocation(18);
		tb.getCurrentPlayer().addOwnedSquare((PropertySquare) SquareFactory.getSquare(tb.getCurrentPlayer().getLocation()));
		StreetSquare Square = (StreetSquare)tb.getCurrentPlayer().getOwnedSquares().get(0);
		assertFalse(tb.isBuyable());
	}

	@Test
	public void testIsBuildable() {
		ArrayList r = new ArrayList<String> ( );
		r.add("test1");
		r.add("test2");
		r.add("test3");
		Board tb = new Board(r);
		tb.getCurrentPlayer().addOwnedSquare((PropertySquare) SquareFactory.getSquare(18));
		for( int i=0;i<5 ;i++) {
			
		}
	}

	@Test
	public void testGetPlayerInfo() {
		fail("Not yet implemented");
	}

}
