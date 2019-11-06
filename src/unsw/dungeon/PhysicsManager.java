package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.event.MovementEvent;

public class PhysicsManager {
	
	private Set<Movable> objects;
	private Set<EventHandler<MovementEvent>> handlers;
	
	public PhysicsManager() {
		this.objects = new HashSet<>();
		this.handlers = new HashSet<>();
	}
	
	public void addMovable(Movable movable) {
		this.objects.add(movable);
	}
	
	public void addHandler(EventHandler<MovementEvent> handler) {
		this.handlers.add(handler);
	}

}
