package unsw.dungeon;

import javafx.scene.image.Image;

public class Exit extends Entity implements EventHandler<MovementEvent> {

	public static Image img = new Image("/exit.png");
	
    public Exit(int x, int y) {
        super(x, y);
    }

	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			// TODO: exit functionality
			System.out.println("EXIT");
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().onMovement(this);
	}

}
