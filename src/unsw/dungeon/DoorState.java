package unsw.dungeon;

public interface DoorState {

	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event);
	
}
