

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChildTest {
	Child c1,c2,c3, c4, c5,c6;

	@Before
	public void setUp() throws Exception {
		//Evening movie (after 6 pm)
		c1 = new Child("Little Mermaid", "G", 5,19,"NONE");
		//Matinee (before 6 pm)
		c2 = new Child("Little Mermaid", "G", 2,12,"NONE");
		//Evening IMAX movie
		c3 = new Child("Frozen", "G", 3,20,"IMAX");
		//Matinee IMAX
		c4 = new Child("Frozen", "G", 1,13,"IMAX");
		//Evening 3D
		c5 = new Child("Show Dogs", "PG", 3,21,"3D");
		//Matinee 3D
		c6 = new Child("Show Dogs", "PG", 2,14,"3D");
	}

	@After
	public void tearDown() throws Exception {
		c1=c2=c3=c4=c5=c6=null;
	}

	@Test
	public void test() {
		assertEquals(11.78,c1.calculateTicketPrice(),.01);
		assertEquals(6.30,c2.calculateTicketPrice(),.01);
		assertEquals(13.97,c3.calculateTicketPrice(),.01);
		assertEquals(8.49,c4.calculateTicketPrice(),.01);
		assertEquals(13.43,c5.calculateTicketPrice(),.01);
		assertEquals(7.95,c6.calculateTicketPrice(),.01);
	}

}
