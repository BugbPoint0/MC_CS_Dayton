import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class creates the hashTable with buckets which will be LinkedLists of CDEs 
 * @author David Dayton
 */
public class CourseDBStructure implements CourseDBStructureInterface {

	int hashTableSize;
	LinkedList <CourseDBElement> [] hashTable;
	
	/**
	 * Arg constructor to initialize hashTableSize
	 * @param hashTableSize The size of the hash table
	 */
	CourseDBStructure(int hashTableSize) {
		this.hashTableSize = hashTableSize;
		hashTable = new LinkedList [hashTableSize];
	}
	
	/**
	 * Arg constructor used for testing
	 * @param hashTableSize The size of the hash table
	 * @param test 
	 */
	CourseDBStructure(String test, int hashTableSize) {
		this.hashTableSize = hashTableSize;
		hashTable = new LinkedList [hashTableSize];
	}
	
	/**
	 * Adds a CDE to the hashTable
	 * @Override
	 * @param element The Course we are adding to the hashTable
	 */
	public void add(CourseDBElement element) {
		int key = Math.abs(element.hashCode()) % hashTableSize;

		if (hashTable[key] == null) {
			hashTable[key] = new LinkedList <CourseDBElement>();
		}
		
		hashTable[key].add(element);
	}

	
	/**
	 * Gets a CDE from the hashTable based on a given CRN 
	 * @Override
	 * @param crn The CRN of the CDE we are trying to get
	 * @return The CDE we are returning based on its hashCode
	 * @throws IOException 
	 */
	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement element = null;
		int key = Integer.toString(crn).hashCode() % hashTableSize;
		
		try {
			if (hashTable[key] != null) {
				for (int x = 0; x < hashTable[key].size(); x++) {
					if (hashTable[key].get(x).crn == crn) {
						element = hashTable[key].get(x);
						return element;
					} 
				}
			} 
			throw new IOException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return element;
	}

	
	/**
	 * A getter for hashTableSize
	 * @Override
	 * @return The size of the hashTable
	 */
	public int getTableSize() {
		return hashTableSize;
	}

	
	/**
	 * Prints out all of this CDS's CDEs
	 * @return A string of all of the CDE's data in this CDS
	 */
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<String>();
		
		for (int x = 0; x < hashTableSize; x++) {
			if (hashTable[x] != null) {
				for (int y = 0; y < hashTable[x].size(); y++) {
					list.add(hashTable[x].get(y).toString());
				}
			}
		}
		
		return list;
	}
	
}
