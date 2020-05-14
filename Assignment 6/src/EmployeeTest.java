

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	Employee e1,e2,e3,e4,e5,e6;

	@Before
	public void setUp() throws Exception {
		//only seem 1 movie this month
		e1 = new Employee("Deadpool", "NR", 5,19,"NONE",12345,1);
		//seen 0 movies this month
		e2 = new Employee("Action Point", "NR", 2,12,"NONE",23456,0);
		//seen 1 movies this month
		e3 = new Employee("Adrift", "PG13", 3,20,"IMAX",34567,1);
		//seen 2 movies
		e4 = new Employee("Book Club", "PG13", 1,13,"IMAX",45678,2);
		//seen 0 movies this month
		e5 = new Employee("Life of the Party", "PG13", 3,21,"3D",56789,0);
		//seen 3 movies this month
		e6 = new Employee("Overboard", "PG13", 2,14,"3D",67890,2);
	}

	@After
	public void tearDown() throws Exception {
		e1=e2=e3=e4=e5=e6=null;
	}

	@Test
	public void test() {
		assertEquals(0.0,e1.calculateTicketPrice(),.01);
		assertEquals(0.0,e2.calculateTicketPrice(),.01);
		assertEquals(0.0,e3.calculateTicketPrice(),.01);
		assertEquals(7.40,e4.calculateTicketPrice(),.01);
		assertEquals(0.0,e5.calculateTicketPrice(),.01);
		assertEquals(7.12,e6.calculateTicketPrice(),.01);
	}

}
