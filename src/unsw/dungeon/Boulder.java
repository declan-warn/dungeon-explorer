package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Boulder extends Entity implements EventHandler<MovementEvent>{
	public static Image img = new Image("/boulder.png");
	
	public Boulder(int x, int y) {
		super(x, y);
	}

	@Override
	public void handle(MovementEvent e) {
		KeyCode direction = e.getDirection();
		if (e.getX() == getX() && e.getY() == getY()) {
	        switch (direction) {
	        case UP:
	        	setY(getY() - 1);
	        	break;
	        case DOWN:
	        	setY(getY() + 1);
	        	break;
	        case LEFT:
	        	setX(getX() - 1);
	        	break;
	        case RIGHT:
	        	setX(getX() + 1);
	            break;
	        default:
	            break;
	        }
		}
	}
	
	public void onDungeonLoad(Dungeon d) {
		d.getPlayer().onMovement(this);
	}
}
