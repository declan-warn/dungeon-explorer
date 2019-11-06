package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import unsw.dungeon.event.EntityReachedPortalEvent;
import unsw.dungeon.event.MovementEvent;

public class Portal extends Entity {
	
	public static Image img = new Image("/portal.png");
	
	private int linkId;

	public Portal(int x, int y, int linkId) {
		super(x, y, "Portal");
		this.linkId = linkId;
	}
	
	public int getLinkId() {
		return this.linkId;
	}
	
	public boolean hasLinkId(int linkId) {
		return this.linkId == linkId;
	}
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		dungeon.registerPortal(this);
	}

	@Override
	public void handle(MovementEvent event) {
		if (event.getX() == getX() && event.getY() == getY()) {
			this.broadcast(new EntityReachedPortalEvent(this, event));
		}
	}

	@Override
	public void accept(EntityVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
