package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;

public class ComplexGoal extends Goal {
	
	public static ComplexGoal allRequired() {
		return new ComplexGoal(new AndCompletionStrategy());
	}
	
	public static ComplexGoal someRequired() {
		return new ComplexGoal(new OrCompletionStrategy());
	}
	
	private Set<Goal> subgoals;
	private GoalCompletionStrategy completionStrategy;
	
	private ComplexGoal(GoalCompletionStrategy completionStrategy) {
		this.subgoals = new HashSet<>();
		this.completionStrategy = completionStrategy;
	}

	@Override
	public boolean isComplete() {
		//return this.subgoals.stream().allMatch(Goal::isComplete);
		return this.completionStrategy.isComplete(subgoals);
	}
	
	public void add(Goal goal) {
		this.subgoals.add(goal);
		goal.addListener(event -> {
			if (this.isComplete()) {
				this.broadcast(new GoalCompletionEvent(this));
			}
		});
	}
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		this.subgoals.forEach(subgoal -> subgoal.onDungeonLoad(dungeon));
	}

}