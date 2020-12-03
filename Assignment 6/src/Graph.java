import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The graph data structure
 * @author David Dayton
 * @param <Town> Vertex type (Town)
 * @param <Road> Edge type (Road)
 */
public class Graph implements GraphInterface<Town, Road> {

	ArrayList<Town> adjacencyList;
	ArrayList<ArrayList<String>> paths;
	
	/**
	 * Constructor
	 */
	Graph() {
		adjacencyList = new ArrayList<Town>();
		paths = new ArrayList<ArrayList<String>>();
	}
	
	/**
	 * Gets the road connecting two towns
	 * @Override
	 * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
	 * @return The road connecting source vertex to target vertex.
	 */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex != null && destinationVertex != null) {
			for (int x = 0; x < sourceVertex.ConnectedRoads.size(); x++) {
				if (sourceVertex.ConnectedRoads.get(x).contains(destinationVertex)) {
					return sourceVertex.ConnectedRoads.get(x);
				}
			}
		} 
		return null;
	}

	
	/**
	 * Adds an edge to the graph
	 * @Override
	 * @param source The first town on the road
	 * @param destination The second town on the road 
	 * @param weight Weight of the edge, i.e., distance from one town to the other
	 * @param description Name of the road
	 * @return The newly created edge if added to the graph, otherwise null.
	 * @throws  if source or target vertices are not found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
	 */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		try {
			if (sourceVertex == null || destinationVertex == null) {
				throw new NullPointerException();
			} else if (!adjacencyList.contains(sourceVertex) && !adjacencyList.contains(destinationVertex)) {
				throw new IllegalArgumentException();
			}
		} catch (NullPointerException exception) {
			exception.getMessage();
		} catch (IllegalArgumentException exception) {
			exception.getMessage();
		}
		
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		return newRoad;
	}

	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never contain duplicate vertices.
     * @Override
     * @param v vertex to be added to this graph.
     * @return true if this graph did not already contain the specified vertex.
     * @throws NullPointerException if the specified vertex is null.
     */
	public boolean addVertex(Town v) {
		if (v != null) {
			adjacencyList.add(v);
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is null, returns false.
     * @Override
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return true if this graph contains the specified edge.
	 */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for (int x = 0; x < sourceVertex.ConnectedRoads.size(); x++) {
			if (sourceVertex.ConnectedRoads.get(x).contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}

	
	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the specified vertex is null returns false.
     * @Override
     * @param v vertex whose presence in this graph is to be tested.
     * @return true if this graph contains the specified vertex.
     */
	public boolean containsVertex(Town v) {
		if (adjacencyList.contains(v)) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     * @Override
     * @return a set of the edges contained in this graph.
     */
	public Set<Road> edgeSet() {
		Set<Road> edgeSet = new HashSet<Road>();
		
		for (int x = 0; x < adjacencyList.size(); x++) {
			for (int y = 0; y < adjacencyList.get(x).ConnectedRoads.size(); y++) {
				if (!edgeSet.contains(adjacencyList.get(x).ConnectedRoads.get(y))) {
					edgeSet.add(adjacencyList.get(x).ConnectedRoads.get(y));
				}
			}
		}
		
		return edgeSet;
	}

	
	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     * @Override
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     * @return a set of all edges touching the specified vertex.
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> edgeSet = new HashSet<Road>();
		
		try {
			if (vertex == null) {
				throw new NullPointerException();
			} else if (!adjacencyList.contains(vertex)) {
				throw new IllegalArgumentException();
			}
		} catch (NullPointerException exception) {
			exception.getMessage();
		} catch (IllegalArgumentException exception) {
			exception.getMessage();
		}
		
		for (int x = 0; x < vertex.ConnectedRoads.size(); x++) {
			if (!edgeSet.contains(vertex.ConnectedRoads.get(x))) {
				edgeSet.add(vertex.ConnectedRoads.get(x));
			}
		}
		
		return edgeSet;
	}

	
	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight > 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     * @Override
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     * @return The removed edge, or null if no edge removed.
     */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		if (!containsEdge(sourceVertex, destinationVertex)) {
			return null;
		}
		
		Road removedRoad = getEdge(sourceVertex, destinationVertex);
		
		if (weight > 1) {
			if (removedRoad.getWeight() != weight) {
				return null;
			}
		}
		
		if (description != null) {
			if (!removedRoad.getName().equals(description)) {
				return null;
			}
		} 
		
		sourceVertex.ConnectedRoads.remove(removedRoad);
		destinationVertex.ConnectedRoads.remove(removedRoad);
		
		return null;
	}

	
	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     * @Override
     * @param v vertex to be removed from this graph, if present.
     * @return true if the graph contained the specified vertex false otherwise.
     */
	public boolean removeVertex(Town v) {
		if (v == null) {
			return false;
		}
		
		if (adjacencyList.contains(v)) {
			for (int y = 0; y < v.adjacentTowns.size(); y++) {
				removeEdge(v, v.adjacentTowns.get(y), v.ConnectedRoads.get(y).getWeight(), v.ConnectedRoads.get(y).getName());
				v.adjacentTowns.get(y).adjacentTowns.remove(v);
			}
			adjacencyList.remove(v);
			return true;
		} else {
			return false;
		}
	}

	
	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     * @Override
     * @return a set view of the vertices contained in this graph.
     */
	public Set<Town> vertexSet() {
		Set<Town> vertexSet = new HashSet<Town>();
		
		for (int x = 0; x < adjacencyList.size(); x++) {
			vertexSet.add(adjacencyList.get(x));
		}
		
		return vertexSet;
	}

	
	/**
	 * @Override
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		
		
		
		return null;
	}

	
	/**
     * Dijkstra's Shortest Path Method. Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * @Override
     */
	public void dijkstraShortestPath(Town sourceVertex) {
		ArrayList<Town> unvistedTowns = new ArrayList<Town>(adjacencyList);
		ArrayList<Town> visitedTowns = new ArrayList<Town>();
		ArrayList<Integer> weights = new ArrayList<Integer>();
		
 
		
		int smallestWeight = Integer.MAX_VALUE;
		int temp = 0;
		Town currentVertex = new Town(sourceVertex);
		
		for (int x = 0; x < unvistedTowns.size(); x++) {
			paths.add(new ArrayList<String>());
		}
		
		paths.get(0).add(sourceVertex.getName() + " via to " + sourceVertex.getName() + "0");
		
		
		for (int x = 1; x < unvistedTowns.size(); x++) {
			for (int y = 0; y < currentVertex.ConnectedRoads.size(); y++) {
				for (int z = 0; z < visitedTowns.size(); z++) {
					if (!currentVertex.ConnectedRoads.get(y).contains(visitedTowns.get(z))) {
						weights.add(currentVertex.ConnectedRoads.get(y).getWeight());
						if (smallestWeight > currentVertex.ConnectedRoads.get(y).getWeight()) {
							smallestWeight = currentVertex.ConnectedRoads.get(y).getWeight();
							temp = y;
						}
					}
				}
			}
			if (currentVertex.ConnectedRoads.get(temp).getSource().equals(currentVertex)) {
				paths.get(x).add(sourceVertex.getName() + " via " + currentVertex.ConnectedRoads.get(temp).getName() + 
						" to " + currentVertex.getName() + " " + weights.get(temp));
				visitedTowns.add(currentVertex);
				unvistedTowns.remove(currentVertex);
				currentVertex = currentVertex.ConnectedRoads.get(temp).getSource();
			} else {
				paths.get(x).add(sourceVertex.getName() + " via " + currentVertex.ConnectedRoads.get(temp).getName() + 
						" to " + currentVertex.getName() + " " + weights.get(temp));
				visitedTowns.add(currentVertex);
				unvistedTowns.remove(currentVertex);
				currentVertex = currentVertex.ConnectedRoads.get(temp).getDestination();
			}
				
		}		
	}

	

}
