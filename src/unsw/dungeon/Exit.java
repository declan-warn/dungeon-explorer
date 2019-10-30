package unsw.dungeon;

import javafx.scene.image.Image;

public class Exit extends Entity implements PlayerMovementObserver {

	public static Image img = new Image("/exit.png");
	
    public Exit(int x, int y) {
        super(x, y);
    }

	@Override
	public void update(PlayerMovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			// TODO: exit functionality
			System.out.println("EXIT");
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().attach(this);
	}

}
