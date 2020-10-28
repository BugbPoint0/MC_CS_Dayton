import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author David Dayton
 */
public class CourseDBManager implements CourseDBManagerInterface {

	CourseDBStructure structure = new CourseDBStructure(20);
	
	/**
	 * adds a CDE with all of the specified data into the CDS
	 * @Override
	 * @param id
	 * @param crn
	 * @param credits
	 * @param roomNum
	 * @param instructor
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		structure.add(element);
	}

	/**
	 * Gets a CDE from the hashTable based on a given CRN 
	 * @Override
	 * @param crn The CRN of the CDE we are trying to get
	 * @return The CDE we are returning based on its hashCode
	 */
	public CourseDBElement get(int crn) {
		try {
			return structure.get(crn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Reads a file with courses and adds the courses into the CDS
	 * @Override
	 * @param input The file we are reading and adding into the CDS
	 */
	public void readFile(File input) throws FileNotFoundException {
		Scanner scan = new Scanner(input);
		
		String courseID;
		String crn;
		String numOfCredits;
		String roomNum;
		String nameOfInstructor;
		
		while (scan.hasNext()) {
			courseID = scan.next();
			crn = scan.next();
			numOfCredits = scan.next();
			roomNum = scan.next();
			nameOfInstructor = scan.nextLine();
			add(courseID, Integer.parseInt(crn), Integer.parseInt(numOfCredits), roomNum, nameOfInstructor);
		}
	}

	
	/**
	 * print out of every CDE in the CDS
	 * @Override
	 * @return An ArrayList which has a print out of every CDE in the CDS
	 */
	public ArrayList<String> showAll() {
		return structure.showAll();
	}

}
