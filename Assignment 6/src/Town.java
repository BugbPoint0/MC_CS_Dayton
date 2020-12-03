import java.util.ArrayList;


/**
 * This class is the vertex in the graph. It has a name and the name if the adjacent towns.
 * @author David Dayton
 */
public class Town implements Comparable<Town> {

	String name;
	ArrayList<Town> adjacentTowns; 
	ArrayList<Road> ConnectedRoads;
	
	/**
	 * Constructor
	 * @param name The name of this town 
	 */
	Town(String name) {
		this.name = name;
		adjacentTowns = new ArrayList<Town>();
		ConnectedRoads = new ArrayList<Road>();
	}
	
	/**
	 * Copy constructor
	 * @param templateTown The town we are copying.
	 */
	Town(Town templateTown) {
		this.name = templateTown.getName();
		adjacentTowns = new ArrayList<Town>();
		ConnectedRoads = new ArrayList<Road>();
	}
	
	
	/**
	 * Compares the names of this town and the one passed in.
	 * @Override
	 * @param o The town we are comparing to this town
	 * @return 0 if names are equal, a positive or negative number if the names are not equal
	 */
	public int compareTo(Town o) {
		if (o.getName().equals(this.getName())) {
			return 0;
		} else {
			return 1;
		}
		
	}

	
	/**
	 * Checks to see two towns names are the same
	 * @Override
	 * @param obj an Object and in this case a town.
	 * @return true if the town names are equal, false if not
	 */
	public boolean equals(Object obj) {
		if (((Town) obj).getName().equals(this.getName())) {
			return true;
		} else {
			return false;
		}
	 }
	
	 
	/**
	 * A getter for Name
	 * @return The name of the town
	 */
	 public String getName() {
		return name;
	 }
	 
	 /**
	  * Adds a town to the adjacentTowns list 
	  * @param t The town that will be added to the adjacentTowns list
	  */
	 public void addToAdjacentTowns(Town t) {
		 adjacentTowns.add(t);
	 }
	 
	 /**
	  * Adds a town to the ConnectedRoads list 
	  * @param r The town that will be added to the ConnectedRoads list
	  */
	 public void addToConnectedRoads(Road r) {
		 ConnectedRoads.add(r);
	 }
	 
	 
	 /**
	  * gets the hashCode for the name of the town
	  * @return the hashcode for the name of the town
	  */
	 public int hashCode() {
		return name.hashCode();
	 }
	
	 
	 /**
	  * Takes this town and turns it variables to strings
	  * @return the town name
	  */
	 public String toString() {
		return this.getName();
	 }
	 
}
