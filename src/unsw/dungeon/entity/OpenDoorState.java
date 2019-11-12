package unsw.dungeon.entity;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.event.MovementEvent;

public class OpenDoorState implements DoorState {
	
	public static Image img = new Image("/door_open.png");
	
	@Override
	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event) {
		// Doesn't need to do anything		
	}

	@Override
	public Image getImage() {
		return OpenDoorState.img;
	}

}
