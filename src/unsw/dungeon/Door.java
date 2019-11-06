package unsw.dungeon;

import javafx.scene.image.Image;
import unsw.dungeon.event.DoorChangeEvent;
import unsw.dungeon.event.MovementEvent;

public class Door extends Entity {

	private DoorState state;
	
	public Door(int x, int y) {
		super(x, y, "Door");
		this.state = new ClosedDoorState();
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
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}
	
}
