package unsw.dungeon.goal;

import java.util.Collection;

public class OrCompletionStrategy implements GoalCompletionStrategy {

	@Override
	public boolean isComplete(Collection<Goal> subgoals) {
		return subgoals.stream().anyMatch(Goal::isComplete);
	}
	
	@Override
	public String toString() {
		return "Complete one of:";
	}
	
}
