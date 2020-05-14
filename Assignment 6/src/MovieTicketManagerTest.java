

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTicketManagerTest {
	private MovieTicketManager ticketList;
	

	@Before
	public void setUp() throws Exception {
		ticketList = new MovieTicketManager();
		
		//add adults
		ticketList.addTicket("Infinity Wars", "PG13", 4,19,"NONE","Adult",0);
		ticketList.addTicket("A Quiet Place", "PG13", 2,12,"NONE","Adult",0);
		ticketList.addTicket("Black Panther", "PG13", 3,14,"3D","Adult",0);
		
		//add children
		ticketList.addTicket("Little Mermaid", "G", 5,19,"NONE","Child",0);
		ticketList.addTicket("Little Mermaid", "G", 2,12,"NONE","Child",0);
		ticketList.addTicket("Show Dogs", "PG", 2,14,"3D","Child",0);
		
		//add employees
		ticketList.addTicket("Deadpool", "NR", 5,19,"NONE","Employee",12345);
		ticketList.addTicket("Action Point", "NR", 2,12,"NONE","Employee",23456);
		ticketList.addTicket("Book Club", "PG13", 1,13,"IMAX","Employee",45678);
		
		//add MoviePass members
		ticketList.addTicket("Deadpool", "NR", 5,19,"NONE","MoviePass",22222);
		ticketList.addTicket("Deadpool", "G", 1,13,"IMAX","MoviePass",55555);
		ticketList.addTicket("Solo", "PG13", 2,12,"NONE","MoviePass",33333);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the number of visits to the theater within the month
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumVisits() {
		//Employee
		assertEquals(1,ticketList.numVisits(12345));
		ticketList.addTicket("Solo", "PG13", 7,19,"NONE","Employee",12345);
		assertEquals(2,ticketList.numVisits(12345));
		ticketList.addTicket("Solo", "PG13", 9,19,"NONE","Employee",12345);
		assertEquals(3,ticketList.numVisits(12345));
		
		//MoviePass member
		assertEquals(1,ticketList.numVisits(33333));
		ticketList.addTicket("Deadpool", "NR", 4,12,"NONE","MoviePass",33333);
		assertEquals(2,ticketList.numVisits(33333));
		ticketList.addTicket("Action Point", "NR", 6,12,"NONE","MoviePass",33333);
		assertEquals(3,ticketList.numVisits(33333));
	}

	/**
	 * Test the number of times this movie has been viewed
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumThisMovie() {
		//Employee
		assertEquals(1,ticketList.numThisMovie(12345,"Deadpool"));
		ticketList.addTicket("Solo", "PG13", 7,19,"NONE","Employee",12345);
		assertEquals(1,ticketList.numThisMovie(12345,"Solo"));
		ticketList.addTicket("Solo", "PG13", 9,19,"NONE","Employee",12345);
		assertEquals(2,ticketList.numThisMovie(12345,"Solo"));

		//MoviePass member
		assertEquals(1,ticketList.numThisMovie(33333,"Solo"));
		ticketList.addTicket("Deadpool", "NR", 4,12,"NONE","MoviePass",33333);
		assertEquals(1,ticketList.numThisMovie(33333,"Deadpool"));
		ticketList.addTicket("Deadpool", "NR", 6,12,"NONE","MoviePass",33333);
		assertEquals(2,ticketList.numThisMovie(33333,"Deadpool"));
	}

	/**
	 * Test the number of movies attended on a day
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumMoviesToday() {
		//Employee
		assertEquals(1,ticketList.numMoviesToday(12345,5));
		ticketList.addTicket("Solo", "PG13", 5,21,"NONE","Employee",12345);
		assertEquals(2,ticketList.numMoviesToday(12345,5));
		ticketList.addTicket("Solo", "PG13", 9,19,"NONE","Employee",12345);
		assertEquals(1,ticketList.numMoviesToday(12345,9));

		//MoviePass member
		assertEquals(1,ticketList.numMoviesToday(33333,2));
		ticketList.addTicket("Deadpool", "NR", 2,15,"NONE","MoviePass",33333);
		assertEquals(2,ticketList.numMoviesToday(33333,2));
		ticketList.addTicket("Deadpool", "NR", 6,12,"NONE","MoviePass",33333);
		assertEquals(1,ticketList.numMoviesToday(33333,6));
	}

	/**
	 * Test adding tickets of the 4 types of tickets
	 */
	@Test
	public void testAddTicket() {
		MovieTicketManager tickets = new MovieTicketManager();
		//adule ticket
		assertEquals(14.80,tickets.addTicket("Infinity Wars", "PG13", 5,19,"NONE","Adult",0),.01);
		//child ticket
		assertEquals(11.78,tickets.addTicket("Little Mermaid", "G", 5,19,"NONE","Child",0),.01);
		//employee ticket - first movie this month
		assertEquals(0.00,tickets.addTicket("Deadpool", "NR", 5,19,"NONE","Employee",12345),.01);
		//moviepass ticket - first movie this month
		assertEquals(9.99,tickets.addTicket("Deadpool", "NR", 5,19,"NONE","MoviePass",22222),.01);
		//moviepass ticket - second movie this month, different movie, different day
		assertEquals(0.00,tickets.addTicket("The Rider", "NR", 6,19,"NONE","MoviePass",22222),.01);
	}

	/**
	 * Test the total of tickets sales for the month
	 */
	@Test
	public void testTotalSalesMonth() {
		assertEquals(101.36,ticketList.totalSalesMonth(),.01);
		//add an employee who has only seen 1 movie - no change
		ticketList.addTicket("Solo", "PG13", 5,21,"NONE","Employee",12345);
		assertEquals(101.36,ticketList.totalSalesMonth(),.01);
		//add a MoviePass member who has seen a movie - no change
		ticketList.addTicket("Deadpool", "NR", 5,15,"NONE","MoviePass",33333);
		assertEquals(101.36,ticketList.totalSalesMonth(),.01);
		//add a MoviePass member who has already seen a movie on this date
		ticketList.addTicket("The Rider", "NR", 5,20,"NONE","MoviePass",33333);
		assertEquals(116.15,ticketList.totalSalesMonth(),.01);
		//add a MoviePass member who has not seen a movie this month
		ticketList.addTicket("Infinity Wars", "PG", 2,14,"NONE","MoviePass",77777);
		assertEquals(126.14,ticketList.totalSalesMonth(),.01);
	}

	/**
	 * The 3D tickets sold this month in chronological order by day
	 */
	@Test
	public void testGet3DTickets() {
		ArrayList<String> result = ticketList.get3DTickets();
		assertTrue("Day 2",result.get(0).contains("Show Dogs"));
		assertTrue("Day 3",result.get(1).contains("Black Panther"));
		ticketList.addTicket("Life of the Party", "PG13", 6,21,"3D","Employee",56789);
		ticketList.addTicket("Adrift", "PG", 1,21,"3D","MoviePass",665544);
		result = ticketList.get3DTickets();
		assertTrue("Day 1",result.get(0).contains("Adrift"));
		assertTrue("Day 2",result.get(1).contains("Show Dogs"));
		assertTrue("Day 3",result.get(2).contains("Black Panther"));
		assertTrue("Day 6",result.get(3).contains("Life of the Party"));
		
	}

	/**
	 * All tickets sold this month in chronological order by day
	 * You don't need to worry about ordering within the day
	 */
	@Test
	public void testGetAllTickets() {
		ArrayList<String> result = ticketList.getAllTickets();
		assertTrue("Day 1",result.get(0).contains("Day: 1"));
		assertTrue("Day 1",result.get(1).contains("Day: 1"));
		assertTrue("Day 2",result.get(2).contains("Day: 2"));
		assertTrue("Day 2",result.get(3).contains("Day: 2"));
		assertTrue("Day 2",result.get(4).contains("Day: 2"));
		assertTrue("Day 2",result.get(5).contains("Day: 2"));
		assertTrue("Day 2",result.get(6).contains("Day: 2"));
		assertTrue("Day 3",result.get(7).contains("Day: 3"));
		assertTrue("Day 4",result.get(8).contains("Day: 4"));
		assertTrue("Day 5",result.get(9).contains("Day: 5"));
		assertTrue("Day 5",result.get(10).contains("Day: 5"));
		assertTrue("Day 5",result.get(11).contains("Day: 5"));
	}

	/**
	 * The MoviePass tickets sold this month in order by MoviePass id
	 */
	@Test
	public void testGetMoviePassTickets() {
		ArrayList<String> result = ticketList.getMoviePassTickets();
		assertTrue("22222",result.get(0).contains("22222"));
		assertTrue("33333",result.get(1).contains("33333"));
		assertTrue("55555",result.get(2).contains("55555"));
		ticketList.addTicket("Life of the Party", "PG13", 3,21,"3D","MoviePass",11223);
		ticketList.addTicket("Infinity Wars", "PG", 3,21,"3D","MoviePass",66554);
		result = ticketList.getMoviePassTickets();
		assertTrue("11223",result.get(0).contains("11223"));
		assertTrue("22222",result.get(1).contains("22222"));
		assertTrue("33333",result.get(2).contains("33333"));
		assertTrue("55555",result.get(3).contains("55555"));
		assertTrue("66554",result.get(4).contains("66554"));
	}

	/**
	 * The monthly sales report
	 */
	@Test
	public void testMonthlySalesReport(){
		String result = ticketList.monthlySalesReport();
		Scanner scan = new Scanner(result);
		assertTrue(scan.nextLine().contains("Monthly Sales Report"));
		scan.nextLine();//blank line
		assertTrue(scan.nextLine().contains("Sales"));
		String line = scan.nextLine(); //Adult line
		assertTrue(line.contains("ADULT"));
		assertTrue(line.contains("40.55"));
		assertTrue(line.contains("3"));
		line = scan.nextLine(); //Child line
		assertTrue(line.contains("CHILD"));
		assertTrue(line.contains("26.03"));
		assertTrue(line.contains("3"));
		line = scan.nextLine(); //Employee line
		assertTrue(line.contains("EMPLOYEE"));
		assertTrue(line.contains("0.00"));
		assertTrue(line.contains("3"));
		line = scan.nextLine(); //Employee line
		assertTrue(line.contains("MOVIEPASS"));
		assertTrue(line.contains("34.78"));
		assertTrue(line.contains("3"));
		
	}
	
	/**
	 * Test readin from a file
	 * @throws FileNotFoundException when file is not found
	 */
	@Test
	public void testReadFile() throws FileNotFoundException {
		PrintWriter testMovie = null;
		MovieTicketManager patrons;
		try {
			testMovie = new PrintWriter("testMovie.txt"); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		testMovie.println("Infinity Wars:PG13:5:19:NONE:Adult:0");
		testMovie.println("Little Mermaid:G:2:12:NONE:Child:0");
		testMovie.println("Action Point:NR:3:12:NONE:Employee:23456");
		testMovie.println("Solo:PG13:6:12:NONE:MoviePass:33333");
		testMovie.close();
		
		patrons = new MovieTicketManager();
		patrons.readFile(new File("testMovie.txt"));
		ArrayList<String> result = patrons.getAllTickets();
		assertTrue("Day 2",result.get(0).contains("Little Mermaid"));
		assertTrue("Day 3",result.get(1).contains("Action Point"));
		assertTrue("Day 5",result.get(2).contains("Infinity Wars"));
		assertTrue("Day 6",result.get(3).contains("Solo"));
		
	}

}
