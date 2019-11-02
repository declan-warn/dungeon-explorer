package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable {

    private Dungeon dungeon;
    private Set<EventHandler<MovementEvent>> movementHandlers = new HashSet<>();

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, "Player");
        this.dungeon = dungeon;
    }
    
    public boolean move(KeyCode keyCode) {
    	MovementEvent event;
    	switch (keyCode) {
    	case LEFT:
    		event = moveLeft(keyCode);
    		break;
    	case RIGHT:
    		event = moveRight(keyCode);
    		break;
    	case UP:
    		event = moveUp(keyCode);
    		break;
    	case DOWN:
    		event = moveDown(keyCode);
    		break;
    	default:
    		return true;
    	}
    	
    	// Will broadcast the event to listeners which are able to cancel it
    	this.broadcastMovement(event);
    	
    	if (!event.isCancelled()) {
			x().set(event.getX());
			y().set(event.getY());
		}
    	
    	return false;
    }
    
    public MovementEvent moveLeft(KeyCode keyCode) {
    	return new MovementEvent(getX() - 1, getY(), keyCode);
    }
    
    public MovementEvent moveRight(KeyCode keyCode) {
    	return new MovementEvent(getX() + 1, getY(), keyCode);
    }
    
    public MovementEvent moveUp(KeyCode keyCode) {
    	return new MovementEvent(getX(), getY() - 1, keyCode);
    }
    
    public MovementEvent moveDown(KeyCode keyCode) {
    	return new MovementEvent(getX(), getY() + 1, keyCode);
    }

	@Override
	public void onMovement(EventHandler<MovementEvent> eventHandler) {
		this.movementHandlers.add(eventHandler);
	}
	
	@Override
	public void broadcastMovement(MovementEvent event) {
		this.movementHandlers.forEach(observer -> observer.handle(event));
		event.triggerEffects();
	}
	
}
