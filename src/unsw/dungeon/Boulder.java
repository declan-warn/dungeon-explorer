package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Boulder extends Entity implements EventHandler<MovementEvent>, Movable {
	public static Image img = new Image("/boulder.png");
    private Set<EventHandler<MovementEvent>> movementHandlers = new HashSet<>();

	public Boulder(int x, int y) {
		super(x, y, "Boulder");
	}

	@Override
	public void handle(MovementEvent e) {
		KeyCode direction = e.getDirection();
		if (e.getX() == getX() && e.getY() == getY()) {
			if (! move(direction)) {
				e.cancel();
			}		
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().onMovement(this);
	}

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
	}

}
