package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.EntityVisitor;

public class EnemiesGoal extends BasicGoal implements EntityVisitor {
	
	private Set<Enemy> enemies;
	
	public EnemiesGoal() {
		super();
		this.enemies = new HashSet<>();
	}

	@Override
	public boolean isComplete() {
		return this.enemies.stream().allMatch(Enemy::isDead);
	}

	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		dungeon.getEntitiesOfType("Enemy").forEach(enemy -> {
			enemy.accept(this);
		});
	}
	
	@Override
	public void visit(Enemy enemy) {
		this.enemies.add(enemy);
		enemy.addListener(event -> {
			if (this.isComplete()) {
				// TODO: do something
				System.out.println("ALL ENEMIES DEAD");
				this.broadcast(new GoalCompletionEvent(this));
			}
		});
	}
	
}
