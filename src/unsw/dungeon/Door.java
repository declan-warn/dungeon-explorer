package unsw.dungeon;

public class Door extends Entity implements EventHandler<MovementEvent> {

	private DoorState state;
	
	public Door(int x, int y) {
		super(x, y);
		this.setState(new ClosedDoorState());
	}

	@Override
	public void handle(MovementEvent event) {
		if (event.getX() == getX() && event.getY() == getY()) {
			this.state.handleCollision(this, this.dungeon, event);
		}
	}
	
	void setState(DoorState state) {
		this.state = state;
	}
	
}
