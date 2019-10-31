package unsw.dungeon;

public class ClosedDoorState implements DoorState {

	@Override
	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event) {
		if (dungeon.hasItem(Key.class)) {
			// TODO: take key
			door.setState(new OpenDoorState());
		} else {
			event.cancel();
		}
	}

}
