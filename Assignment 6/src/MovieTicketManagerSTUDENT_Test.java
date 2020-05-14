

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTicketManagerSTUDENT_Test {
	private MovieTicketManager ticketList;
	

	@Before
	public void setUp() throws Exception {
		ticketList = new MovieTicketManager();
		
		//Student add adult tickets
		ticketList.addTicket("Star Wars", "PG13", 7,11,"NONE","Adult",0);
		//Student add children tickets
		ticketList.addTicket("Brave Little Toaster", "G", 9,12,"NONE","Child",0);
		//Student add employee tickets
		ticketList.addTicket("Red Dead", "NR", 10,20,"NONE","Employee",45);
		//Student add MoviePass member tickets
		ticketList.addTicket("Forward Until Dawn", "R", 12,12,"NONE","MoviePass",54321);
	}

	@After
	public void tearDown() throws Exception {
		//Student set ticketList to null;
		ticketList = null;
	}

	/**
	 * Student Test the number of visits to the theater within the month
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumVisits() {
		//Student test Employees' number of visits
		assertEquals(1,ticketList.numVisits(54321));
		//Student test MoviePass members' number of visits
		assertEquals(1,ticketList.numVisits(45));
	}

	/**
	 * Student Test the number of times this movie has been viewed
	 * This only applied to those who have id numbers - Employees or MoviePass members
	 */
	@Test
	public void testNumThisMovie() {
		//Student test Employees' number of views
		assertEquals(1,ticketList.numThisMovie(45,"Red Dead"));
		//Student test MoviePass members' number of views
		assertEquals(1,ticketList.numThisMovie(54321,"Forward Until Dawn"));
	}

}
