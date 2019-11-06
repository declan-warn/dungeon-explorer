package unsw.dungeon.event;

import unsw.dungeon.entity.Door;

public class DoorChangeEvent implements Event {

	private Door door;
	
	public DoorChangeEvent(Door door) {
		this.door = door;
	}
	
	public Door getDoor() {
		return this.door;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}
	
}
