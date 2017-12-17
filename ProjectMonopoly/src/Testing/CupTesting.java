package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Cup;
import domain.SpeedDie;
import junit.framework.Assert;

class CupTesting {

	@BeforeEach
	void setUp() throws Exception {
		Cup cup =new Cup();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRollAllDice() {
		Cup cup=new Cup();

		assertTrue(( 2 < cup.rollAllDice() && cup.rollAllDice() < 16));

	}

	@Test
	void testRollRegularDice() {
		Cup cup=new Cup();

		assertTrue(( 1 < cup.rollRegularDice() && cup.rollRegularDice() < 13));

	}

	

}
