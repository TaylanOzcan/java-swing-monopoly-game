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
	void testCreate() {
		Player p1 = new Player("Ali");
		assertEquals(p1.getBalance(), 3200);
		assertEquals(p1.getLocation(), 0);
		assertEquals(p1.getName(), "Ali");
	}
	
	@Test
	void testMove() {
		Player p1 = new Player("Ahmet");
		int initLoc = p1.getLocation();
		p1.move(5);
		int finalLoc = p1.getLocation();
		assertNotEquals(initLoc, finalLoc);
	}
	
	@Test
	void testBuy(){
		Player p1 = new Player("Mehmet");
		int initBalance = p1.getBalance();
		p1.move(18);
		PropertySquare square = (PropertySquare)SquareFactory.getSquare(p1.getLocation());
		p1.buy();
		int finalBalance = p1.getBalance();
		assertEquals(finalBalance-initBalance, square.getPrice());
		assertEquals(p1.getOwnedSquares().get(0), square);
	}
	
	@Test
	void testAddCard() {
		Player p1 = new Player("Ayse");
		p1.addChanceCard("chanceCard");
		assertNotNull(p1.getChanceCards());
		assertEquals(p1.getChanceCards().get(0), "chanceCard");		
	}
	
	@Test
	void testDeleteCard() {
		Player p1 = new Player("Ayse");
		p1.addChanceCard("chanceCard");
		p1.deleteChanceCard("ch");
		assertEquals(p1.getChanceCards().size(),1);	
		p1.deleteChanceCard("chanceCard");
		assertEquals(p1.getChanceCards().size(),0);	
	}


}
