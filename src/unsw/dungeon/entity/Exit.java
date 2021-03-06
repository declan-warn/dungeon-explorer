package unsw.dungeon.entity;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import unsw.dungeon.event.MovementEvent;
import unsw.dungeon.event.PlayerReachedExitEvent;

public class Exit extends Entity {
	
    public Exit(int x, int y) {
        super(x, y, "Exit");
    }

	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			this.broadcast(new PlayerReachedExitEvent(true));
		} else if (e.getEntity().getX() == getX() && e.getEntity().getY() == getY()) {
			this.broadcast(new PlayerReachedExitEvent(false));
		}
	}

	@Override
	public Image getImage() {
		return new Image("/beacon.png");
	}

}
