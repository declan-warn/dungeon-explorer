package unsw.dungeon;

public class PlayerReachedExitEvent implements Event {

	private boolean atExit;
	
	public PlayerReachedExitEvent(boolean atExit) {
		this.atExit = atExit;
	}
	
	public boolean isAtExit() {
		return this.atExit;
	}
	
}
