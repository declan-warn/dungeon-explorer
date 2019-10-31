package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class Inventory<T> {

	private Set<T> items = new HashSet<>();
	
	// TODO: does this violate polymorphism?
	public boolean contains(Class<? extends T> c) {
		return items.stream().anyMatch(item -> item.getClass().equals(c));
	}
	
	public T get(Class<? extends T> c) {
		return items.stream().filter(item -> item.getClass().equals(c)).findFirst().orElse(null);
	}
	
	public void add(T item) {
		this.items.add(item);
	}
	
	public void remove(T item) { 
		this.items.remove(item);
	}
	
}
