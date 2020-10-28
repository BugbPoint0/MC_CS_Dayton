

/**
 * This class is pretty much the embodiment of a course (Has all of its info and what not). 
 * @author David Dayton
 */
public class CourseDBElement implements Comparable {

	String courseID;
	int crn;
	

	int numOfCredits;
	String roomNum;
	String nameOfInstructor;
	
	/**
	 * default constructor
	 */
	CourseDBElement () {
		courseID = "";
		crn = 0;
		numOfCredits = 0;
		roomNum = "";
		nameOfInstructor = "";
	}
	
	/**
	 * Constructor to initialize each of CDE's instance variables
	 * @param courseID
	 * @param crn
	 * @param numOfCredits
	 * @param roomNum
	 * @param nameOfInstructor
	 */
	CourseDBElement (String courseID, int crn, int numOfCredits, String roomNum, String nameOfInstructor) {
		this.courseID = courseID;
		this.crn = crn;
		this.numOfCredits = numOfCredits;
		this.roomNum = roomNum;
		this.nameOfInstructor = nameOfInstructor;
	}
	
	/**
	 * @Override
	 * Compares course elements based on CRN number
	 * @param element a CourseDBElement
	 * @return a negative integer if x.compareTo(y) < 0, zero if x.compareTo(y) == 0,
	 * and a positive integer if x.compareTo(y) > 0.
	 */
	public int compareTo(CourseDBElement element) {
		int returnNum;
		
		if (this.crn < element.crn) {
			returnNum = -1;
		} else if (this.crn > element.crn) {
			returnNum = 1;
		} else {
			returnNum = 0;
		}
		
		return returnNum;
	}

	/**
	 * returns the hashCode from this elements CRN number
	 * @return the hashCode coming from CRN
	 */
	public int hashCode() {
		int code;
		code = Integer.toString(crn).hashCode();
		return code;
	}
	
	/**
	 * Prints out this CDE's data
	 * @return A string of all of the data in this CDE
	 */
	public String toString() {
		return ("\nCourse:" + courseID + " CRN:" + crn + " Credits:" + numOfCredits + " Instructor:" + nameOfInstructor + " Room:" + roomNum);
	}
	
	/**
	 * A getter for CRN
	 * @return this instances CRN
	 */
	public int getCRN() {
		return crn;
	}
	
	/**
	 * A setter for CRN
	 * @param crn The int we want to set CRN to
	 */
	public void setCRN(int crn) {
		this.crn = crn;
	}
	
	
}
