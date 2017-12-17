package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Cup;
import domain.Player;
import domain.SpeedDie;
import junit.framework.Assert;

class CupTesting {

	@Test
	public void testRollAllDice() {
		Cup cup=new Cup();

		assertTrue(( 2 < cup.rollAllDice() && cup.rollAllDice() < 16));

	}

	@Test
	public void testRollRegularDice() {
		Cup cup=new Cup();

		assertTrue(( 1 < cup.rollRegularDice() && cup.rollRegularDice() < 13));

	}
	@Test
	public void testClearFaceValues() {
		Cup cup=new Cup();
		cup.clearFaceValues();
		assertSame(-3 , cup.getRegDie1Value() );
		assertSame(-3 , cup.getRegDie2Value() );
		assertSame(-3 , cup.getSpeedValue() );
		
	}
	
	@Test
	public void testRepOk() {
		Cup cup=new Cup();
		assertTrue(cup.repOk());
	}

}
