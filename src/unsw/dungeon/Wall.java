package unsw.dungeon;

public class Wall extends Entity implements EventHandler<MovementEvent> {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			e.cancel();
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().onMovement(this);
	}

}
