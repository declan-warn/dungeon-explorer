package unsw.dungeon;

import javafx.scene.image.Image;

public class ClosedDoorState implements DoorState {
	
	public static Image img = new Image("/closed_door.png");

	@Override
	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event) {
		if (dungeon.hasItem(Key.class)) {
			dungeon.takeItem(dungeon.getItem(Key.class));
			door.setState(new OpenDoorState());
		} else {
			event.cancel();
		}
	}

	@Override
	public Image getImage() {
		return ClosedDoorState.img;
	}

}
