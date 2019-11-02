package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

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
	
	public void onDungeonLoad(Dungeon dungeon) {
		this.objects.forEach(object -> {
			this.handlers.forEach(handler -> {
				object.onMovement(handler);
			});
		});
	}

}
