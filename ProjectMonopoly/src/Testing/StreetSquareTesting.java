package Testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

import domain.StreetSquare;

class StreetSquareTesting {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void testHouse() {
		StreetSquare s1 = new StreetSquare(0, null, 0, 0, 0, 0, 0, 0, 0, 0, null);
		s1.build();
		int bb = s1.getnHouses();
	    assertNotEquals(1, bb);
		
	}
	@Test
	void testHotel() {
		StreetSquare s2 = new StreetSquare(0, null, 0, 0, 0, 0, 0, 0, 0, 0, null);
		s2.build();
		s2.build();
		s2.build();
		s2.build();
		s2.build();
		int b = s2.getnHotels();
	    assertNotEquals(1, b);
		
	}
	@Test
	void testSkyscraper() {
		StreetSquare s3 = new StreetSquare(0, null, 0, 0, 0, 0, 0, 0, 0, 0, null);
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		s3.build();
		
	
		int bhh = s3.getnSkyscrapers();
	    assertNotEquals(1, bhh);
		
	
	}
}
