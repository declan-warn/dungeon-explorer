package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.event.GoalCompletionEvent;

public class ComplexGoal extends Goal {
	
	private boolean wasComplete = false;
	
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
	}
	
	@Override
	public void handle(GoalCompletionEvent event) {
		if (this == event.getGoal()) return;
		if (this.isComplete() && !this.wasComplete) {
			this.wasComplete = true;
			this.broadcast(new GoalCompletionEvent(this));
		}
	}
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		this.subgoals.forEach(subgoal -> subgoal.onDungeonLoad(dungeon));
	}

	@Override
	public Iterator<Goal> iterator() {
		return this.subgoals.iterator();
	}
	
	@Override
	public boolean hasSubgoals() {
		return true;
	}
	
	@Override
	public String toString() {
		return this.completionStrategy.toString();
	}

}
