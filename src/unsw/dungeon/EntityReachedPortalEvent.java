package unsw.dungeon;

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
	
	public int getLinkId() {
		return this.portal.getLinkId();
	}
	
	public boolean hasLinkId(int linkId) {
		return this.portal.hasLinkId(linkId);
	}
	
	public Entity getEntity() {
		return this.movement.getEntity();
	}
	
	public MovementEvent getMovement() {
		return this.movement;
	}
	
}
