package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;

public class Door extends Entity implements EventHandler<MovementEvent>, EventEmitter<DoorChangeEvent> {

	private DoorState state;
	private Set<EventHandler<DoorChangeEvent>> openListeners = new HashSet<>();
	
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
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		dungeon.getPlayer().onMovement(this);
	}
	
	void setState(DoorState state) {
		this.state = state;
		this.broadcast(new DoorChangeEvent(this));
	}

	@Override
	public void addListener(EventHandler<DoorChangeEvent> eventHandler) {
		this.openListeners.add(eventHandler);
	}

	@Override
	public void removeListener(EventHandler<DoorChangeEvent> eventHandler) {
		this.openListeners.remove(eventHandler);
	}
	
	private void broadcast(DoorChangeEvent event) {
		this.openListeners.forEach(listener -> listener.handle(event));
	}
	
	public Image getImage() {
		return this.state.getImage();
	}
	
}
