package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.EntityVisitor;
import unsw.dungeon.event.EnemyDeathEvent;
import unsw.dungeon.event.GoalCompletionEvent;

public class EnemiesGoal extends BasicGoal {
	
	private int enemiesTotal;
	private int enemiesKilled;
	
	public EnemiesGoal() {
		super();
		this.enemiesTotal = 0;
		this.enemiesKilled = 0;
	}
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		this.enemiesTotal = dungeon.getEntitiesOfType("Enemy").size();
	}

	@Override
	public boolean isComplete() {
		return this.enemiesTotal == this.enemiesKilled;
	}
	
	@Override
	public void handle(EnemyDeathEvent event) {
		this.enemiesKilled++;
		if (this.isComplete()) {
			System.out.println("ALL ENEMIES DEAD");
			this.broadcast(new GoalCompletionEvent(this));
		}
	}
	
}
