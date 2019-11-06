package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import unsw.dungeon.event.MovementEvent;
import unsw.dungeon.event.PlayerReachedExitEvent;

public class Exit extends Entity implements EventHandler<MovementEvent> {

	public static Image img = new Image("/exit.png");
	
	private Set<EventHandler<PlayerReachedExitEvent>> reachedHandlers = new HashSet<>();
	
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
	
	public void onDungeonLoad(Dungeon d) {
		super.onDungeonLoad(d);
		d.registerMovementHandler(this);
	}
	
	@Override
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}

}
