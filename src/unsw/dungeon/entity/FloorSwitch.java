 package unsw.dungeon.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import unsw.dungeon.event.MovementEvent;
import unsw.dungeon.event.SwitchActivationEvent;

public class FloorSwitch extends Entity {
	
	private boolean activated = false;
	
	public FloorSwitch(int x, int y) {
        super(x, y, "FloorSwitch");
    }

	@Override
	public void handle(MovementEvent e) {
		if (e.getX() == getX() && e.getY() == getY()) {
			activated = true;
			this.broadcast(new SwitchActivationEvent(this));
		} else {
			if (e.getEntity().getEntityType().equals("Boulder")) {
					KeyCode direction = e.getDirection();
					switch (direction) {
					case LEFT:
						if ((e.getX() + 1) == getX() && e.getY() == getY()) {
							activated = false;
							this.broadcast(new SwitchActivationEvent(this));
						}  			
						break;
					case RIGHT:
						if ((e.getX() - 1) == getX() && e.getY() == getY()) {
							activated = false;
							this.broadcast(new SwitchActivationEvent(this));
						} 
						break;
					case UP:
						if (e.getX() == getX() && (e.getY() + 1) == getY()) {
							activated = false;
							this.broadcast(new SwitchActivationEvent(this));
						} 
						break;
					case DOWN:
						if (e.getX() == getX() && (e.getY() - 1) == getY()) {
							activated = false;
							this.broadcast(new SwitchActivationEvent(this));
						} 
						break;
					default:
						break;
					}
					System.out.println(this.activated + " " + this.getX());
			}
		}
	}
	
	public boolean getStatus() {
		return this.activated;
	}
	
	public boolean isActivated() {
		return this.activated;
	}

	@Override
	public Image getImage() {
		return new Image("/painting.png");
	}
	
}
