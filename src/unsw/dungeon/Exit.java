package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;

public class Exit extends Entity implements EventHandler<MovementEvent>, EventEmitter<PlayerReachedExitEvent> {

	public static Image img = new Image("/exit.png");
	
	private Set<EventHandler<PlayerReachedExitEvent>> reachedHandlers = new HashSet<>();
	
    public Exit(int x, int y) {
        super(x, y, "Exit");
    }

	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			this.broadcast(new PlayerReachedExitEvent());
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		super.onDungeonLoad(d);
		d.registerMovementHandler(this);
	}

	@Override
	public void addListener(EventHandler<PlayerReachedExitEvent> eventHandler) {
		this.reachedHandlers.add(eventHandler);
	}
	
	@Override
	public void removeListener(EventHandler<PlayerReachedExitEvent> eventHandler) {
		this.reachedHandlers.remove(eventHandler);
	}

	private void broadcast(PlayerReachedExitEvent event) {
		this.reachedHandlers.forEach(handler -> handler.handle(event));
	}
	
	@Override
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}

}
