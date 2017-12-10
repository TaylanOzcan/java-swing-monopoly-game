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
	void testGetTotalValue() {
		Cup cup=new Cup();
		Assert.assertTrue(( 2 < cup.getTotalValue() && cup.getTotalValue() < 16));
	}

	@Test
	void testGetRegularValue() {
		Cup cup=new Cup();
		Assert.assertTrue(( 1 < cup.getTotalValue() && cup.getTotalValue() < 13));
	}

	

}
