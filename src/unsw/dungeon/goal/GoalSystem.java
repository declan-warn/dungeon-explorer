package unsw.dungeon.goal;

public class GoalSystem {
	
	private ComplexGoal dungeonGoal;
	
	public GoalSystem() {
//		this.dungeonGoal = new ComplexGoal();
	}
	
	public void register(Goal goal) {
		this.dungeonGoal.add(goal);
	}

}
