package unsw.dungeon.entity;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.collectable.CollectableEntity;
import unsw.dungeon.entity.collectable.Item;
import unsw.dungeon.event.ItemUseEvent;
import unsw.dungeon.event.MovementEvent;

public class ClosedDoorState implements DoorState {
	
	public static Image img = new Image("/door_closed.png");

	@Override
	public void handleCollision(Door door, Dungeon dungeon, MovementEvent event) {
		if (dungeon.hasItem(Item.KEY)) {
			CollectableEntity key = dungeon.getItem(Item.KEY);
			dungeon.takeItem(key);
			dungeon.broadcastEvent(new ItemUseEvent(key, Item.KEY));
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
