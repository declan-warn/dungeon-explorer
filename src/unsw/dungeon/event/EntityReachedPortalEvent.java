package unsw.dungeon.event;

import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Portal;

public class EntityReachedPortalEvent implements Event {
	
	private Portal portal;
	private MovementEvent movement;
	
	public EntityReachedPortalEvent(Portal portal, MovementEvent movement) {
		this.portal = portal;
		this.movement = movement;
	}
	
	public Portal getPortal() {
		return this.portal;
	}
	
	public int getId() {
		return this.portal.getId();
	}
	
	public boolean hasId(int id) {
		return this.portal.hasId(id);
	}
	
	public Entity getEntity() {
		return this.movement.getEntity();
	}
	
	public MovementEvent getMovement() {
		return this.movement;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}
	
}
