package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;

public class Door extends Entity implements EventHandler<MovementEvent>, EventEmitter<DoorOpenEvent> {

	private DoorState state;
	private Set<EventHandler<DoorOpenEvent>> openListeners = new HashSet<>();
	
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

	@Override
	public void addListener(EventHandler<DoorOpenEvent> eventHandler) {
		this.openListeners.add(eventHandler);
	}

	@Override
	public void removeListener(EventHandler<DoorOpenEvent> eventHandler) {
		this.openListeners.remove(eventHandler);
	}
	
	private void broadcast(DoorOpenEvent event) {
		this.openListeners.forEach(listener -> listener.handle(event));
	}
	
	public Image getImage() {
		return this.state.getImage();
	}
	
}
