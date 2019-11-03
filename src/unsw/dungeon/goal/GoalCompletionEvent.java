package unsw.dungeon.goal;

import unsw.dungeon.Event;

public class GoalCompletionEvent implements Event {

	private Goal goal;
	
	public GoalCompletionEvent(Goal goal) {
		this.goal = goal;
	}
	
	public Goal getGoal() {
		return this.goal;
	}
	
}
