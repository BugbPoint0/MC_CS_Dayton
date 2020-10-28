import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is my student test file for the CourseDBManager
 * @author David Dayton
 */
public class CourseDBManager_STUDENT_Test {

	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("DogTrainging101",101010,88,"DogHall","Hippo House MuhGee");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("DogTrainging101",101010,15,"DoggieHall","Hippo House MuhGee");
		dataMgr.add("HowToCookAKrabbyPatty",30559,3,"KrustyKrab","Mr. Krabs");
		ArrayList<String> list = dataMgr.showAll();
		
		assertEquals(list.get(0),"\nCourse:DogTrainging101 CRN:101010 Credits:15 Instructor:Hippo House MuhGee Room:DoggieHall");
		assertEquals(list.get(1),"\nCourse:HowToCookAKrabbyPatty CRN:30559 Credits:3 Instructor:Mr. Krabs Room:KrustyKrab");
			}
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("HowToCookAKrabbyPatty 30504 4 KrustyKrab Mr. Krabs");
			inFile.print("HowToKaratayy 30503 4 SandysTreeDome Sandy Cheeks");
			
			inFile.close();
			dataMgr.readFile(inputFile);
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}


