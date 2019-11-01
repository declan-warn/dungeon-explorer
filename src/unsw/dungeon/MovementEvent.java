package unsw.dungeon;

public class MovementEvent implements Event {

	private Entity entity; 
	public int x;
	public int y;
	private Boolean cancelled;

	MovementEvent(Entity entity, int x, int y) {
		this.entity = entity;
		this.x = x;
		this.y = y;
		this.cancelled = false;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Entity getEntity() {
		return this.entity;
	}

	public void cancel() {
		this.cancelled = true;
	}

	public Boolean isCancelled() {
		return cancelled;
	}

}
