package unsw.dungeon.entity;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.event.EntityReachedPortalEvent;
import unsw.dungeon.event.MovementEvent;

public class Portal extends Entity implements Paired {
	
	public static Image img = new Image("/command_block.png");
	
	private final int id;

	public Portal(int x, int y, int id) {
		super(x, y, "Portal");
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		dungeon.registerPortal(this);
	}

	@Override
	public void handle(MovementEvent event) {
		if (event.getX() == getX() && event.getY() == getY()) {
			this.broadcast(new EntityReachedPortalEvent(this, event));
		}
	}

}
