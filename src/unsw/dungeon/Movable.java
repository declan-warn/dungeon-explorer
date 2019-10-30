package unsw.dungeon;

import javafx.scene.input.KeyCode;

public interface Movable {
	
	public void move(KeyCode keyCode);
	
	public void onMovement(EventHandler<MovementEvent> eventHandler);
	
	public void broadcastMovement(MovementEvent event);
	
}
