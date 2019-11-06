package unsw.dungeon;

import javafx.scene.image.Image;
import unsw.dungeon.event.MovementEvent;

public interface DoorState {

	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event);
	
	public Image getImage();
	
}
