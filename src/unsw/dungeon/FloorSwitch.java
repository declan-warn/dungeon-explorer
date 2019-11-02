package unsw.dungeon;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class FloorSwitch extends Entity implements EventHandler<MovementEvent> {
	
	public static Image img = new Image("/pressure_plate.png");
	
	private boolean activated = false;
	
	public FloorSwitch(int x, int y) {
        super(x, y, "FloorSwitch");
    }

	@Override
	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			activated = true;
		} else {
			if (e.wouldCollide(this)) {
				e.andThen((event) -> {
					KeyCode direction = e.getDirection();
					switch (direction) {
					case LEFT:
						if ((e.getX() + 1) == getX() && e.getY() == getY()) {
							activated = false;
						}  			
						break;
					case RIGHT:
						if ((e.getX() - 1) == getX() && e.getY() == getY()) {
							activated = false;
						} 
						break;
					case UP:
						if (e.getX() == getX() && (e.getY() + 1) == getY()) {
							activated = false;
						} 
						break;
					case DOWN:
						if (e.getX() == getX() && (e.getY() - 1) == getY()) {
							activated = false;
						} 
						break;
					default:
						break;
					}
				});
			}
		}
		System.out.println(activated);
	}
	
	public void onDungeonLoad(Dungeon d) {
		List<Boulder> boulders = d.getBoulders();
		boulders.forEach(boulder -> { 
			boulder.onMovement(this);
		});
	}
	
	public boolean getStatus() {
		return this.activated;
	}
}
