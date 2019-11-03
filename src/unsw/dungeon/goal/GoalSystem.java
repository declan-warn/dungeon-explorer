package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;

public class GoalSystem {
	
	private Goal dungeonGoal;
	
	public void set(Goal goal) {
		this.dungeonGoal = goal;
	}
	
	public void onDungeonLoad(Dungeon dungeon) {
		this.dungeonGoal.onDungeonLoad(dungeon);
	}

}
