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
	public void testGetCurrentPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRollDice() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testMortgage() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildHouse() {
		fail("Not yet implemented");
	}

	@Test
	public void testSqueeze() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrade() {
		fail("Not yet implemented");
	}

	@Test
	public void testSell() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsBuyable() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsBuildable() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerInfo() {
		fail("Not yet implemented");
	}

}
