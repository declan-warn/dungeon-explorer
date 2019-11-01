package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

class ComplexGoal implements Goal {
	
	private Set<Goal> subgoals;
	
	public ComplexGoal() {
		this.subgoals = new HashSet<>();
	}

	@Override
	public boolean isComplete() {
		return this.subgoals.stream().allMatch(Goal::isComplete);
	}
	
	public void add(Goal goal) {
		this.subgoals.add(goal);
	}

}
