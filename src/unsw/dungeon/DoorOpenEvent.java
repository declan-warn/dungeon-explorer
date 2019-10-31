package unsw.dungeon;

public class DoorOpenEvent implements Event {

	private Door door;
	
	public DoorOpenEvent(Door door) {
		this.door = door;
	}
	
	public Door getDoor() {
		return this.door;
	}
	
}
