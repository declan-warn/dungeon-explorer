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
        super(x, y);
        this.dungeon = dungeon;
    }
    
    public void move(KeyCode keyCode) {
    	MovementEvent event;
    	switch (keyCode) {
    	case LEFT:
    		event = moveLeft();
    		break;
    	case RIGHT:
    		event = moveRight();
    		break;
    	case UP:
    		event = moveUp();
    		break;
    	case DOWN:
    		event = moveDown();
    		break;
    	default:
    		return;
    	}
    	
    	// Will broadcast the event to listeners which are able to cancel it
    	this.broadcastMovement(event);
    	
    	if (!event.isCancelled()) {
			x().set(event.getX());
			y().set(event.getY());
		}
    }
    
    public MovementEvent moveLeft() {
    	return new MovementEvent(this, getX() - 1, getY());
    }
    
    public MovementEvent moveRight() {
    	return new MovementEvent(this, getX() + 1, getY());
    }
    
    public MovementEvent moveUp() {
    	return new MovementEvent(this, getX(), getY() - 1);
    }
    
    public MovementEvent moveDown() {
    	return new MovementEvent(this, getX(), getY() + 1);
    }

	@Override
	public void onMovement(EventHandler<MovementEvent> eventHandler) {
		this.movementHandlers.add(eventHandler);
	}
	
	@Override
	public void broadcastMovement(MovementEvent event) {
		this.movementHandlers.forEach(observer -> observer.handle(event));
	}
	
}
