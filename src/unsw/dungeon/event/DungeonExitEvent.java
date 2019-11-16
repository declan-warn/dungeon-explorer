package unsw.dungeon.event;

import unsw.dungeon.entity.ExitStatus;

public class DungeonExitEvent implements Event {
	
	private ExitStatus status;
	
	public DungeonExitEvent(ExitStatus status) {
		this.status = status;
	}
	
	public ExitStatus getStatus() {
		return this.status;
	}
	
	public boolean isStatus(ExitStatus status) {
		return this.status == status;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}

}
