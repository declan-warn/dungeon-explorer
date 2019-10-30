package unsw.dungeon;

public class MovementEvent implements Event {

	public int x;
	public int y;
	private Boolean cancelled;

	MovementEvent(int x, int y) {
		this.x = x;
		this.y = y;
		this.cancelled = false;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void cancel() {
		this.cancelled = true;
	}

	public Boolean isCancelled() {
		return cancelled;
	}

}
