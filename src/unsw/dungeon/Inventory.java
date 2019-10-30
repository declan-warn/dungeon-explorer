package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class Inventory {

	private Set<CollectableEntity> items = new HashSet<>();
	
	// TODO: does this violate polymorphism?
	public boolean contains(Class<? extends CollectableEntity> c) {
		return items.stream().anyMatch(item -> item.getClass().equals(c));
	}
	
	public void add(CollectableEntity item) {
		this.items.add(item);
	}
	
	public void remove(CollectableEntity item) { 
		this.items.remove(item);
	}
	
}
