package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class PhysicsManager {
	
	private Set<Movable> objects;
	
	public PhysicsManager() {
		this.objects = new HashSet<>();
	}
	
	public void addMovable(Movable movable) {
		this.objects.add(movable);
	}
	
	public void addHandler(EventHandler<MovementEvent> handler) {
		this.objects.forEach(object -> object.onMovement(handler));
	}

}
