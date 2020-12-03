

/**
 * The class Road that can represent the edges of a Graph of Towns.
 * @author David Dayton
 */
public class Road implements Comparable<Road> {

	Town source;
	Town destination;
	int weight;
	String name;
	
	/**
	 * Constructor
	 * @param source The first town on the road
	 * @param destination The second town on the road 
	 * @param weight Weight of the edge, i.e., distance from one town to the other
	 * @param name Name of the road
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
		source.adjacentTowns.add(destination);
		destination.addToAdjacentTowns(source);
		source.addToConnectedRoads(this);
		destination.addToConnectedRoads(this);
	}

	/**
	 * Constructor with weight preset to 1
	 * @param source The first town on the road
	 * @param destination The second town on the road 
	 * @param name Name of the road
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.name = name;
		source.addToAdjacentTowns(destination);
		destination.addToAdjacentTowns(source);
		source.addToConnectedRoads(this);
		destination.addToConnectedRoads(this);
	}
	
	/**
	 * Checks to see if two roads are the same 
	 * @param o The road whose name is being compared to this one
	 * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
	 */
	public int compareTo(Road o) {
		if (o.getName().equals(this.getName())) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * Getter for name
	 * @return The name of the road
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for destination
	 * @return A town on the road
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * Getter for source
	 * @return A town on the road
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * Getter for weight
	 * @return Returns the distance of the road
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Checks to see if this town is connected to this road
	 * @param town a vertex of the graph
	 * @return true only if the edge is connected to the given vertex or town
	 */
	public boolean contains(Town town) {
		if (town.compareTo(source) == 0 || town.compareTo(destination) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * prints this roads name I think
	 * @return A string version of this road
	 */
	public String toString() {
		return this.getName();
	}
	
	/**
	 * Checks to see if two roads are the same
	 * @param r road object to compare it to
	 * @return True if each of the ends of the road r is the same as the ends of this road. 
	 */
	public boolean equals(Object r) {
		if (this.contains(((Road) r).getDestination()) && this.contains(((Road) r).getSource())) {
			return true;
		} else {
			return false;
		}
	}
	
}
