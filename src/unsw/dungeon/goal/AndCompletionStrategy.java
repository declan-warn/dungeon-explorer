package unsw.dungeon.goal;

import java.util.Collection;

public class AndCompletionStrategy implements GoalCompletionStrategy {

	@Override
	public boolean isComplete(Collection<Goal> subgoals) {
		return subgoals.stream().allMatch(Goal::isComplete);
	}

}
