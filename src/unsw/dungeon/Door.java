package unsw.dungeon;

import javafx.scene.image.Image;
import unsw.dungeon.event.DoorChangeEvent;

public class Door extends Entity implements EventHandler<MovementEvent> {

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
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		dungeon.registerMovementHandler(this);
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
