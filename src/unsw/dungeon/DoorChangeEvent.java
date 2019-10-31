package unsw.dungeon;

public class DoorChangeEvent implements Event {

	private Door door;
	
	public DoorChangeEvent(Door door) {
		this.door = door;
	}
	
	public Door getDoor() {
		return this.door;
	}
	
}
