package unsw.dungeon;

import java.util.List;

public class Wall extends Entity implements EventHandler<MovementEvent> {

    public Wall(int x, int y) {
        super(x, y, "Wall");
    }

	@Override
	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			e.cancel();
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.registerMovementHandler(this);
	}
	
	@Override
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}
}
