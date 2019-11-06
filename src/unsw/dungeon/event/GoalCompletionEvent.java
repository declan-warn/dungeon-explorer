package unsw.dungeon.event;

import unsw.dungeon.goal.Goal;

public class GoalCompletionEvent implements Event {

	private Goal goal;
	
	public GoalCompletionEvent(Goal goal) {
		this.goal = goal;
	}
	
	public Goal getGoal() {
		return this.goal;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}
	
}
