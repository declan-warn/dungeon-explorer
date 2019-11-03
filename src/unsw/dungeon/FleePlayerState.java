package unsw.dungeon;

import javafx.scene.input.KeyCode;

public class FleePlayerState implements EnemyState {

	@Override
	public void move(Enemy enemy, Player player) {
		int targetX = player.getX();
		int targetY = player.getY();
		
		int dx = (targetX - enemy.getX());
		int dy = (targetY - enemy.getY());
		
		if (dx == 0 && dy == 0) {
			return;
		} else if (Math.abs(dx) > Math.abs(dy)) {
			if (dx < 0) {
				enemy.move(KeyCode.RIGHT);
			} else {
				enemy.move(KeyCode.LEFT);
			}
		} else {
			if (dy < 0) {
				enemy.move(KeyCode.DOWN);
			} else {
				enemy.move(KeyCode.UP);
			}
		}
	}

}
