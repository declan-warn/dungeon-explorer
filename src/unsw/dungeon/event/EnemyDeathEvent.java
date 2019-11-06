package unsw.dungeon.event;

import unsw.dungeon.Enemy;

public class EnemyDeathEvent implements Event {

	private Enemy enemy;
	
	public EnemyDeathEvent(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public Enemy getEnemy() {
		return this.enemy;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}
	
}
