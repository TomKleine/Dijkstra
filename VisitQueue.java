package dijkstra;

import java.util.ArrayList;

/**
 * @author TomKleine
 * This class behaves like a PriorityQueue,
 * except for the fact that every DijkstraPath object in the queue
 * is always sorted after a new DijsktraPath is added. It also does
 * not allow for multiple DijsktraPath objects with the same start and end node.
 */
public class VisitQueue{
	
	private final ArrayList<DijkstraPath> queue = new ArrayList<>();
	
	public void add(DijkstraPath object){
		if(contains(object)){   //A DijkstraPath already exists
			if(get(object).compareTo(object) > 0){  //Does the provided DijkstraPath have a lower (=better) distance?
				queue.set(indexOf(object), object);
			}
		}else{
			queue.add(object);
			queue.sort(DijkstraPath::compareTo);
		}
	}
	
	public DijkstraPath get(int index){
		return queue.get(index);
	}
	
	public DijkstraPath get(DijkstraPath p){
		for(DijkstraPath p1 : queue){
			if(p1.end.equals(p.end)){
				return p1;
			}
		}
		return null;
	}
	
	public boolean contains(DijkstraPath p){
		for(DijkstraPath p1 : queue){
			if(p.end.equals(p1.end)) return true;
		}
		return false;
	}
	
	public int indexOf(DijkstraPath p){
		for(int i = 0; i < queue.size(); i++){
			if(queue.get(i).end.equals(p.end)) return i;
		}
		return -1;
	}
	
	/**
	 * This method returns the next DijkstraPath object with the lowest distance without removing it from the queue
	 * @return the next DijkstraPath with the lowest distance
	 */
	public DijkstraPath peek(){
		return queue.size() > 0 ? queue.get(0) : null;
	}
	
	/**
	 * This method returns the next DijkstraPath object with the lowest distance and removing it from the queue
	 * @return the next DijkstraPath with the lowest distance
	 */
	public DijkstraPath poll(){
		if(queue.size() > 0){
			DijkstraPath obj = queue.get(0);
			queue.remove(obj);
			return obj;
		}
		return null;
	}
	
	public int size(){
		return queue.size();
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
}