package unsw.dungeon;

public class Wall extends Entity implements PlayerMovementObserver {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public void update(PlayerMovementEvent e) {
//		System.out.println("(" + this.getX() + "," + this.getY() + ")");
		if (e.getX() ==(this.getX()) &&
				e.getY() == (getY())) {
			e.cancel();
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().attach(this);
	}

}
