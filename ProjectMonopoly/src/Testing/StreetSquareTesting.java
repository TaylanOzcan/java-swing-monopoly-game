package Testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import domain.Player;
import domain.StreetSquare;

class StreetSquareTesting {

	

	@Test
	void testHouse() {
		Player s = new Player("amammam");
		StreetSquare s1 = new StreetSquare(0, "mf", 0, 0, 0, 0, 0, 0, 0, 0, new Color(12,21,22));
		s1.setOwner(s);
		s1.build();
		int bb = s1.getnHouses();
	    assertEquals(1, bb);
		
	}
	@Test
	void testHotel() {
		Player ff = new Player("adfs");
		StreetSquare s2 = new StreetSquare(0, "abc", 0, 0, 0, 0, 0, 0, 0, 0, new Color(12,21,22));
		s2.setOwner(ff);
		s2.build();  // #1.ev
		s2.build();  // #2.ev
		s2.build();  // #3.ev
		s2.build();  // #4.ev
		s2.build();  // #1.hotel
		int b = s2.getnHotels();
		
	    assertEquals(1, b);
		
	}
	@Test
	void testSkyscraper() {
		Player djfka = new Player("afs");
		StreetSquare s3 = new StreetSquare(0, "ffkk", 0, 0, 0, 0, 0, 0, 0, 0,  new Color(12,21,22));
		s3.setOwner(djfka);
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		
	
		int bhh = s3.getnSkyscrapers();
	    assertEquals(1, bhh);
		
	
	}
}
