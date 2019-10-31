package unsw.dungeon;

import javafx.scene.image.Image;

public class OpenDoorState implements DoorState {
	
	public static Image img = new Image("/open_door.png");
	
	@Override
	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event) {
		// Doesn't need to do anything		
	}

	@Override
	public Image getImage() {
		return OpenDoorState.img;
	}

}
