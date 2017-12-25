package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Player;
import domain.PropertySquare;
import domain.Square;
import domain.SquareFactory;
import domain.StreetSquare;

class PlayerTesting {

	@Test
	public void testCreate() {
		Player p1 = new Player(0, "Ali");
		assertEquals(p1.getBalance(), 3200);
		assertEquals(p1.getLocation(), 0);
		assertEquals(p1.getName(), "Ali");
	}
	
	@Test
	public void testMove() {
		Player p1 = new Player(1, "Ahmet");
		int initLoc = p1.getLocation();
		p1.move(5);
		int midLoc = p1.getLocation();
		assertEquals(midLoc - initLoc, 5);
		p1.move(3);
		int finalLoc = p1.getLocation();
		assertEquals(finalLoc - initLoc, 8);
	}
	
	@Test
	public void testBuy(){
		Player p1 = new Player(2, "Mehmet");
		int initBalance = p1.getBalance();
		p1.move(18);
		PropertySquare square = (PropertySquare)SquareFactory.getInstance().getSquare(p1.getLocation());
		p1.buy();
		int finalBalance = p1.getBalance();
		assertNotEquals(finalBalance, initBalance);
		assertEquals(p1.getOwnedSquares().get(0), square);
	}
	
	@Test
	public void testAddCard() {
		Player p1 = new Player(3, "Ayse");
		p1.addChanceCard("chanceCard");
		assertNotNull(p1.getChanceCards());
		assertEquals(p1.getChanceCards().get(0), "chanceCard");		
	}
	
	@Test
	public void testDeleteCard() {
		Player p1 = new Player(4, "Ayse");
		p1.addChanceCard("chanceCard");
		p1.deleteChanceCard("ch");
		assertEquals(p1.getChanceCards().size(),1);	
		p1.deleteChanceCard("chanceCard");
		assertEquals(p1.getChanceCards().size(),0);	
	}
	
	@Test
	public void testRepOk() {
		Player p1 = new Player(5, "Ayse");
		assertTrue(p1.repOk());
	}

}
