package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Observable {

    private Dungeon dungeon;
    private Set<Observer> observers = new HashSet<>();

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
    	PlayerMovementEvent e;
    	switch (keyCode) {
    	case LEFT:
    		e = moveLeft();
    		break;
    	case RIGHT:
    		e = moveRight();
    		break;
    	case UP:
    		e = moveUp();
    		break;
    	case DOWN:
    		e = moveDown();
    		break;
    	default:
    		return;
    	}
    	
    	// Will broadcast the event to listeners which are able to cancel it
    	this.notifyObservers(e);
    	
    	if (!e.isCancelled()) {
			x().set(e.getX());
			y().set(e.getY());
		}
    }
    
    public PlayerMovementEvent moveLeft() {
    	return new PlayerMovementEvent(getX() - 1, getY());
    }
    
    public PlayerMovementEvent moveRight() {
    	return new PlayerMovementEvent(getX() + 1, getY());
    }
    
    public PlayerMovementEvent moveUp() {
    	return new PlayerMovementEvent(getX(), getY() - 1);
    }
    
    public PlayerMovementEvent moveDown() {
    	return new PlayerMovementEvent(getX(), getY() + 1);
    }

	@Override
	public void attach(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void detach(Observer o) {
		this.observers.remove(o);
	}

	public void notifyObservers(PlayerMovementEvent e) {
		this.observers.forEach(observer -> observer.update(e));
	}
}
