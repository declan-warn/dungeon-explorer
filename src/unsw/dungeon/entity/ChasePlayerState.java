package unsw.dungeon.entity;

import javafx.scene.input.KeyCode;

public class ChasePlayerState implements EnemyState {

	@Override
	public void move(Enemy enemy, Player player) {
		if (player.isInvincible()) {
			enemy.setState(new FleePlayerState());
			return;
		}

		
		int targetX = player.getX();
		int targetY = player.getY();
		
		int dx = (targetX - enemy.getX());
		int dy = (targetY - enemy.getY());
		
		if (dx == 0 && dy == 0) {
			return;
		} else if (Math.abs(dx) > Math.abs(dy)) {
			if (dx < 0) {
				enemy.move(KeyCode.LEFT);
			} else {
				enemy.move(KeyCode.RIGHT);
			}
		} else {
			if (dy < 0) {
				enemy.move(KeyCode.UP);
			} else {
				enemy.move(KeyCode.DOWN);
			}
		}
	}

}
