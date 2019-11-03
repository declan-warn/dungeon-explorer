package unsw.dungeon;

public class EnemyDeathEvent implements Event {

	private Enemy enemy;
	
	public EnemyDeathEvent(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public Enemy getEnemy() {
		return this.enemy;
	}
	
}
