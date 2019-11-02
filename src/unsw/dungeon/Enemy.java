package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Enemy extends Entity implements Movable {
	
	public static Image img = new Image("/deep_elf_master_archer.png");
	
	private Set<EventHandler<MovementEvent>> movementHandlers = new HashSet<>();

	private boolean dead = false;
	
	public Enemy(int x, int y) {
		super(x, y, "Enemy");
	}
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		dungeon.registerMovable(this);
	}
	
	@Override
	public void tick() { 
		int targetX = this.dungeon.getPlayer().getX();
		int targetY = this.dungeon.getPlayer().getY();
		
		int dx = (targetX - this.getX());
		int dy = (targetY - this.getY());
		
		if (dx == 0 && dy == 0) {
			return;
		} else if (Math.abs(dx) > Math.abs(dy)) {
			if (dx < 0) {
				this.move(KeyCode.LEFT);
			} else {
				this.move(KeyCode.RIGHT);
			}
		} else {
			if (dy < 0) {
				this.move(KeyCode.UP);
			} else {
				this.move(KeyCode.DOWN);
			}
		}
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
	public void onMovement(EventHandler<MovementEvent> eventHandler) {
		this.movementHandlers.add(eventHandler);
	}
	
	@Override
	public void broadcastMovement(MovementEvent event) {
		this.movementHandlers.forEach(observer -> observer.handle(event));
		event.triggerEffects();
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
			x().set(event.getX());
			y().set(event.getY());
			
			if (event.wouldCollide(dungeon.getPlayer()) && !dead) {
				if (dungeon.hasItem(Item.SWORD)) {
					dead = true;
					this.x().set(this.dungeon.getWidth());
					Sword sword = (Sword) dungeon.getItem(Item.SWORD);
					sword.decreasetotalHitsLeft();
					if (sword.gettotalHitsLeft() == 0) {
						dungeon.takeItem((CollectableEntity) sword); 
					}
				} else {
					dungeon.getPlayer().kill();
				}
			}
		}
    	
    	return false;
	}

}
