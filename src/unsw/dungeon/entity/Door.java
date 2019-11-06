package unsw.dungeon.entity;

import javafx.scene.image.Image;
import unsw.dungeon.event.DoorChangeEvent;
import unsw.dungeon.event.MovementEvent;

public class Door extends Entity implements Paired {

	private DoorState state;
	private final int id;
	
	public Door(int x, int y, int id) {
		super(x, y, "Door");
		this.state = new ClosedDoorState();
		this.id = id;
	}

	@Override
	public void handle(MovementEvent event) {
		if (event.getX() == getX() && event.getY() == getY()) {
			this.state.handleCollision(this, this.dungeon, event);
		}
	}
	
	void setState(DoorState state) {
		this.state = state;
		this.broadcast(new DoorChangeEvent(this));
	}
	
	public Image getImage() {
		return this.state.getImage();
	}

	@Override
	public int getId() {
		return this.id;
	}
	
}
