package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class Inventory {

	private Set<CollectableEntity> items = new HashSet<>();
	
	// TODO: does this violate polymorphism?
	public boolean contains(Item itemType) {
		return items.stream().anyMatch(item -> item.isType(itemType));
	}
	
	public CollectableEntity get(Item itemType) {
		return items.stream().filter(item -> item.isType(itemType)).findFirst().orElse(null);
	}
	
	public void add(CollectableEntity item) {
		this.items.add(item);
	}
	
	public void remove(CollectableEntity item) { 
		this.items.remove(item);
	}
	
}
