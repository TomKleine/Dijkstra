package dijkstra;

/**
 * @author TomKleine
 */
public class DijkstraNode{
	
	protected final String id;
	
	public DijkstraNode(String id){
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof DijkstraNode)) return false;
		if(this == o) return true;
		
		DijkstraNode node = (DijkstraNode)o;
		return this.id.equals(node.id);
	}
	
	public String getId(){
		return id;
	}
	
	@Override
	public String toString(){
		return "DijkstraNode(" + id + ")";
	}
}
