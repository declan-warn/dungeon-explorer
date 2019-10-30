package unsw.dungeon;

public class Wall extends Entity implements PlayerMovementObserver {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public void update(PlayerMovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			e.cancel();
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().attach(this);
	}

}
