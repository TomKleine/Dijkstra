package dijkstra;

import java.util.ArrayList;

/**
 * @author TomKleine
 */
public class Dijkstra{
	
	/**
	 * This method calculates the shortest path between two nodes given a list of paths
	 * @param from the start node
	 * @param to the destination node
	 * @param paths a list of paths
	 * @return a DijkstraPath object representing the shortest path
	 */
	public static DijkstraPath getShortedPath(DijkstraNode from, DijkstraNode to, ArrayList<DijkstraPath> paths){
		
		//Clone the paths to prevent mutating the original
		
		//Is the path to itself?
		if(from.equals(to)){
			return new DijkstraPath(from, to, 0);
		}
		
		//Setup the data structures which keep track of the visited paths and the yet to visit nodes
		ArrayList<DijkstraPath> visited = new ArrayList<>();
		VisitQueue toVisit = new VisitQueue();
		
		//Load all neighbours for the start node
		getNeighbours(from, paths).forEach(toVisit::add);
		
		DijkstraNode previous;
		
		while(!toVisit.isEmpty()){
			//Advance to nearest neighbouring Node and save that path to the 'visited' list
			DijkstraPath bestPath = toVisit.poll();
			
			previous = bestPath.end;
			visited.add(bestPath);
			
			if(bestPath.end.equals(to)){//Found shortest path to destination
				//Construct a Path object from the visited paths (including a list of intermediate nodes)
				ArrayList<DijkstraNode> intermediates = new ArrayList<>();
				DijkstraNode last = to;
				
				//Backtrack the shortest path from the 'visited' list, adding each node as an intermediary
				for(int i = visited.size() - 1; i >= 0; i--){
					if(visited.get(i).end.equals(last)){
						if(!last.equals(to)){
							intermediates.add(last);
						}
						last = visited.get(i).start;
					}
				}
				
				DijkstraPath finalPath = new DijkstraPath(from, to, bestPath.distance);
				
				//Flip the intermediates (otherwise order will be in reverse)
				for(int i = intermediates.size() - 1; i >= 0; i--){
					finalPath.through(intermediates.get(i));
				}
				
				return finalPath;
			}
			
			//Get all neighbouring paths for the previous node
			for(DijkstraPath p1 : getNeighbours(previous, paths)){
				if(!p1.end.equals(previous) && !isVisited(p1.end, visited)){   //Prevent going back directly to the previous node and prevent visiting already visited nodes
					//Create a copy of the original path, as to not mutate the original
					DijkstraPath p2 = p1.copy();
					p2.lengthenBy(getDistanceFrom(previous, visited));
					toVisit.add(p2);
				}
			}
		}
		
		return new DijkstraPath(from, to, 0);
	}
	
	/**
	 * This method gets the best (lowest) distance known to a given node
	 * @param node the node to get the distance for
	 * @param paths the known paths
	 * @return the best (lowest) distance if a path exits, otherwise '0'
	 */
	private static int getDistanceFrom(DijkstraNode node, ArrayList<DijkstraPath> paths){
		for(DijkstraPath p : paths){
			if(p.end.equals(node)) return p.distance;
		}
		return 0;
	}
	
	/**
	 * This method checks if a given @code{DijkstraNode}
	 * @param node the node to check for
	 * @param visited the list of visited nodes
	 * @return if the node is visited
	 */
	private static boolean isVisited(DijkstraNode node, ArrayList<DijkstraPath> visited){
		for(DijkstraPath p : visited){
			if(p.end.equals(node)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method gets the direct neighbouring paths for a given Node
	 * @param origin the node from which a path must start
	 * @param paths a list of the available paths
	 * @return a list of direct neighbouring paths
	 */
	private static ArrayList<DijkstraPath> getNeighbours(DijkstraNode origin, ArrayList<DijkstraPath> paths){
		ArrayList<DijkstraPath> neighbours = new ArrayList<>();
		for(DijkstraPath p : paths){
			if(p.start.equals(origin)){
				neighbours.add(p);
			}
		}
		return neighbours;
	}
}