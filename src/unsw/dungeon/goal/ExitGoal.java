package unsw.dungeon.goal;

import unsw.dungeon.event.GoalCompletionEvent;
import unsw.dungeon.event.PlayerReachedExitEvent;

public class ExitGoal extends BasicGoal {
	
	private boolean isComplete = false;

	@Override
	public boolean isComplete() {
		return this.isComplete;
	}
	
	@Override
	public void handle(PlayerReachedExitEvent event) {
		this.isComplete = event.isAtExit();
		if (this.isComplete()) {
			System.out.println("DONE EXIT GOAL");
			this.broadcast(new GoalCompletionEvent(this));
		}
	}

}
