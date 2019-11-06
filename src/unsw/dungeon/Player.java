package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;
import unsw.dungeon.event.MovementEvent;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable {

    private Dungeon dungeon;
    private boolean isInvincible;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, "Player");
        this.dungeon = dungeon;
        this.isInvincible = false;
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
    	this.broadcast(event);
    	
    	if (!event.isCancelled()) {
			x().set(event.getX());
			y().set(event.getY());
		}
    	
    	return false;
    }
    

    public MovementEvent moveLeft(KeyCode keyCode) {
    	return new MovementEvent(getX() - 1, getY(), keyCode, this);
    }
    
    public MovementEvent moveRight(KeyCode keyCode) {
    	return new MovementEvent(getX() + 1, getY(), keyCode, this);
    }
    
    public MovementEvent moveUp(KeyCode keyCode) {
    	return new MovementEvent(getX(), getY() - 1, keyCode, this);
    }
    
    public MovementEvent moveDown(KeyCode keyCode) {
    	return new MovementEvent(getX(), getY() + 1, keyCode, this);
    }
	
	@Override
	public void kill() {
		// TODO: probably emit an event which a sword can cancel if it's in the inventory
		System.out.println("PLAYER DIED");
		this.dungeon.exit(ExitStatus.FAILURE);
	}
	
	@Override
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}
	
	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}
	
	public boolean isInvincible() {
		return this.isInvincible;
	}
	
}
