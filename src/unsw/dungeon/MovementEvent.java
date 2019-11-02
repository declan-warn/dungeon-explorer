package unsw.dungeon;

import javafx.scene.input.KeyCode;

public class MovementEvent implements Event {

	private int x;
	private int y;
	private KeyCode direction;
	private Boolean cancelled;
	private String flag = null;
	
	MovementEvent(int x, int y, KeyCode direction) {
		this.x = x;
		this.y = y;
		this.cancelled = false;
		this.direction = direction;
	}
	
	MovementEvent(int x, int y, KeyCode direction, String flag) {
		this.x = x;
		this.y = y;
		this.cancelled = false;
		this.direction = direction;
		this.flag = flag;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
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

}
