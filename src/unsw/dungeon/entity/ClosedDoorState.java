package unsw.dungeon.entity;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.event.MovementEvent;

public class ClosedDoorState implements DoorState {
	
	public static Image img = new Image("/closed_door.png");

	@Override
	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event) {
		if (dungeon.hasItem(Item.KEY)) {
			dungeon.takeItem(dungeon.getItem(Item.KEY));
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
