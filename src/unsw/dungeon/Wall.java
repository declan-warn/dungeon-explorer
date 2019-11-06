package unsw.dungeon;

import unsw.dungeon.event.MovementEvent;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y, "Wall");
    }

	@Override
	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			e.cancel();
		}
	}
	
}
