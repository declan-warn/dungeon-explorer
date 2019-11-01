package unsw.dungeon.goal;

import java.util.Collection;

public interface GoalCompletionStrategy {

	public boolean isComplete(Collection<Goal> subgoals);
	
}
