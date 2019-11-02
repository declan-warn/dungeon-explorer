package unsw.dungeon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Boulder extends Entity implements EventHandler<MovementEvent>, Movable {
	public static Image img = new Image("/boulder.png");
    private Set<EventHandler<MovementEvent>> movementHandlers = new HashSet<>();
    
    //I changed the constructor for entities to include the type as in the Dungeon class i have a get Boulder entities to allow stuff to observe the boulder idk if this is bad but
    //look at the dungeon method cuz i feel might cause merge error and look at the onDungeonload of door and wall thats the majority of the change
	public Boulder(int x, int y) {
		super(x, y, "Boulder");
	}

	@Override
	public void handle(MovementEvent e) {
	    //Added a direction field to MovementEvent as need to know which side its being pushed from e.g. if player pushes boulder to the right boulder might need to know to move
	    //Basically calls move in the direction the boulder should move and returns true/false if the boulder has moved and then cancels if the boulder hasn't moved
		KeyCode direction = e.getDirection();
		if (e.getX() == getX() && e.getY() == getY()) {
			if (e.getFlag() != null && this.getEntityType().equals("Boulder")) {
				e.cancel();
				return;
			}
			if (! move(direction)) {
				e.cancel();
			}		
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().onMovement(this);
		List<Boulder> boulders = d.getBoulders();
		boulders.forEach(boulder -> {
			if (boulder.getX() == this.getX() || boulder.getY() == this.getY()) {
				boulder.onMovement(this);
			}
		});
	}
    //Changed move to return a boolean in the movable interface since wasn't sure how to know if the boulder moved or not not sure if bad/another way to implement
	@Override
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
			switch (keyCode) {
			case UP:
				setY(getY() - 1);
				break;
			case DOWN:
				setY(getY() + 1);
				break;
			case LEFT:
				setX(getX() - 1);
				break;
			case RIGHT:
				setX(getX() + 1);
				break;
			default:
				break;
			}
			return true;
		}
    	
    	return false;
    }
    
    public MovementEvent moveLeft(KeyCode keyCode) {
    	return new MovementEvent(getX() - 1, getY(), keyCode, "boulder");
    }
    
    public MovementEvent moveRight(KeyCode keyCode) {
    	return new MovementEvent(getX() + 1, getY(), keyCode, "boulder");
    }
    
    public MovementEvent moveUp(KeyCode keyCode) {
    	return new MovementEvent(getX(), getY() - 1, keyCode, "boulder");
    }
    
    public MovementEvent moveDown(KeyCode keyCode) {
    	return new MovementEvent(getX(), getY() + 1, keyCode, "boulder");
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
