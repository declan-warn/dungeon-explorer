package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import unsw.dungeon.event.EnemyDeathEvent;
import unsw.dungeon.event.MovementEvent;

public class Enemy extends Entity implements Movable, EntityVisitor {
	
	public static Image img = new Image("/deep_elf_master_archer.png");
	
	private EnemyState state;

	private boolean dead = false;
	
	public Enemy(int x, int y) {
		super(x, y, "Enemy");
		this.state = new ChasePlayerState();
	}
	
	@Override
	public void tick() {
		this.state.move(this, this.dungeon.getPlayer());
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
			
			if (event.wouldCollide(dungeon.getPlayer()) && !dead) {
				if (dungeon.hasItem(Item.SWORD)) {
					dead = true;
					this.broadcast(new EnemyDeathEvent(this));
					this.x().set(this.dungeon.getWidth());
					Sword sword = (Sword) dungeon.getItem(Item.SWORD);
					sword.decreasetotalHitsLeft();
					if (sword.gettotalHitsLeft() == 0) {
						dungeon.takeItem((CollectableEntity) sword); 
					}
				} else if (this.dungeon.getPlayer().isInvincible()) {
					dead = true;
					this.broadcast(new EnemyDeathEvent(this));
					this.x().set(this.dungeon.getWidth());
				} else {
					dungeon.getPlayer().kill();
				}
			}
		}
    	
    	return false;
	}

	@Override
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public void setState(EnemyState state) {
		this.state = state;
	}

}
