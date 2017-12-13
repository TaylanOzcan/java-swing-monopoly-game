package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.SpeedDie;
import junit.framework.Assert;

class SpeedDieTesting {

	@BeforeEach
	public void setUp() throws Exception {
		SpeedDie Speeddie =new SpeedDie();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSpeedDie() {
		assertNotNull(new SpeedDie());	
	}

	@Test
	void testGetValue() {
		SpeedDie Speeddie =new SpeedDie();
		Random rnd = new Random ();
		assertTrue(( -3 < Speeddie.roll() && Speeddie.roll() < 4));
	}

	@Test
	void testDoSpecialMrMonopoly()  {
		SpeedDie Speeddie =new SpeedDie();
		if (Speeddie.roll() == -1 || Speeddie.roll() == 0)
			assertEquals(Speeddie.DoSpecial(),"Mr Monopoly");
		if (Speeddie.roll() == -2)
			assertEquals(Speeddie.DoSpecial(),"Bus Icon");

	}

	@Test
	void testGetCurrentValue() {
		SpeedDie Speeddie =new SpeedDie();
		assertTrue(( -3 < Speeddie.roll() && Speeddie.roll() < 4));
	}

}
