import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {
	
	private GradeBook bookOne = new GradeBook(5);
	private GradeBook bookTwo = new GradeBook(5);
	
	@BeforeEach
	void setUp() throws Exception {
		bookOne.addScore(99.0);
		bookOne.addScore(77.0);
		bookOne.addScore(56.0);
		bookTwo.addScore(23.0);
		bookTwo.addScore(76.0);
	}

	@AfterEach
	void tearDown() throws Exception {
		bookOne = null;
		bookTwo = null;
	}

	@Test
	void testAddScore() {
		assertTrue(bookOne.toString().contentEquals("99.0 77.0 56.0 0.0 0.0 "));
		assertEquals(2, bookTwo.getScoreSize());
	}

	@Test
	void testSum() {
		assertEquals(99, bookTwo.sum());
	}

	@Test
	void testMinimum() {
		assertEquals(56, bookOne.minimum());
	}

	@Test
	void testFinalScore() {
		assertEquals(176, bookOne.finalScore());
	}

}
