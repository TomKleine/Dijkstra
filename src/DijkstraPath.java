package dijkstra;

import java.util.ArrayList;

/**
 * @author TomKleine
 */
public class DijkstraPath implements Comparable<DijkstraPath>{
	
	protected int distance;
	protected final DijkstraNode start, end;
	protected final ArrayList<DijkstraNode> intermediates;
	
	public DijkstraPath(DijkstraNode start, DijkstraNode end, int distance){
		this(start, end, distance, new ArrayList<>());
	}
	
	public DijkstraPath(DijkstraNode start, DijkstraNode end, int distance, ArrayList<DijkstraNode> intermediates){
		this.start = start;
		this.end = end;
		this.distance = distance;
		this.intermediates = intermediates;
	}
	
	public final DijkstraNode getStart(){
		return start;
	}
	
	public final DijkstraNode getEnd(){
		return end;
	}
	
	/**
	 * This method adds an intermediate node to the list
	 * This method can be used for presenting a full path through one or mode (intermediate) nodes
	 * @param node the node to add
	 */
	public void through(DijkstraNode node){
		intermediates.add(node);
	}
	
	public final int getDistance(){
		return distance;
	}
	
	/**
	 * This method increases the distance by a given amount
	 * @param distance the distance to add
	 */
	public final void lengthenBy(int distance){
		this.distance += distance;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof DijkstraPath)) return false;
		if(this == o) return true;
		
		DijkstraPath path = (DijkstraPath)o;
		return this.start.equals(path.start) && this.end.equals(path.end);
	}
	
	@Override
	public int compareTo(DijkstraPath p){
		return Integer.compare(distance, p.distance);
	}
	
	/**
	 * This method copies a given DijkstraPath object into a new one
	 * @return a new DijkstraPath object
	 */
	public DijkstraPath copy(){
		return new DijkstraPath(start, end, distance, intermediates);
	}
	
	@Override
	public String toString(){
		return "DijkstraPath(" + start + ", " + end + ", " + distance + ", [" + intermediates + "])";
	}
}
