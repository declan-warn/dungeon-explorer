package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.scene.input.KeyCode;

public class MovementEvent implements Event {

	private Entity entity;
	private int x;
	private int y;
	private KeyCode direction;
	private Boolean cancelled;
	private String flag = null;
	
	private List<Consumer<MovementEvent>> effects;

	MovementEvent(int x, int y, KeyCode direction, Entity entity) {
		this.x = x;
		this.y = y;
		this.cancelled = false;
		this.direction = direction;
		this.effects = new ArrayList<>();
		this.entity = entity;
	}
	
	MovementEvent(int x, int y, KeyCode direction, Entity entity, String flag) {
		this(x, y, direction, entity);
		this.flag = flag;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public String getFlag() {
		return this.flag;
	}
	
	public KeyCode getDirection() {
		return this.direction;
	}

	public void cancel() {
		this.cancelled = true;
	}

	public Boolean isCancelled() {
		return cancelled;
	}
	
	public boolean wouldCollide(Entity entity) {
		return this.getX() == entity.getX() && this.getY() == entity.getY();
	}
	
	public void andThen(Consumer<MovementEvent> effect) {
		this.effects.add(effect);
	}
	
	public void triggerEffects() {
		if (!this.isCancelled()) {
			this.effects.forEach(effect -> effect.accept(this));
		}
	}
	
	public boolean isPlayer() {
		return this.entity.getEntityType().equals("Player");
	}

}
