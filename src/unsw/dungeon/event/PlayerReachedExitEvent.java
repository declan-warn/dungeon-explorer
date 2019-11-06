package unsw.dungeon.event;

public class PlayerReachedExitEvent implements Event {

	private boolean atExit;
	
	public PlayerReachedExitEvent(boolean atExit) {
		this.atExit = atExit;
	}
	
	public boolean isAtExit() {
		return this.atExit;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}
	
}
