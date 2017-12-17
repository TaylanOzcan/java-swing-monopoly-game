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
		Player p = new Player("a");
		StreetSquare s1 = new StreetSquare(0, "mf", 0, 0, 0, 0, 0, 0, 0, 0, "");
		s1.setOwner(p);
		s1.build();
		int nh = s1.getnHouses();
	    assertEquals(1, nh);
		
	}
	@Test
	void testHotel() {
		Player p = new Player("aa");
		StreetSquare s2 = new StreetSquare(0, "abc", 0, 0, 0, 0, 0, 0, 0, 0, "");
		s2.setOwner(p);
		s2.build();  // #1.ev
		s2.build();  // #2.ev
		s2.build();  // #3.ev
		s2.build();  // #4.ev
		s2.build();  // #1.hotel
		int nh = s2.getnHotels();
	    assertEquals(1, nh);
	}
	
	@Test
	void testSkyscraper() {
		Player p = new Player("aaa");
		StreetSquare s3 = new StreetSquare(0, "ffkk", 0, 0, 0, 0, 0, 0, 0, 0, "");
		s3.setOwner(p);
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		int ns = s3.getnSkyscrapers();
	    assertEquals(1, ns);
	}
}
