package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;

public class Portal extends Entity implements EventHandler<MovementEvent>, EventEmitter<EntityReachedPortalEvent> {
	
	public static Image img = new Image("/portal.png");
	
	private int linkId;
	private Set<EventHandler<EntityReachedPortalEvent>> reachedListeners;

	public Portal(int x, int y, int linkId) {
		super(x, y, "Portal");
		this.linkId = linkId;
		this.reachedListeners = new HashSet<>();
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
		dungeon.getPlayer().onMovement(this);
	}

	@Override
	public void handle(MovementEvent event) {
		if (event.getX() == getX() && event.getY() == getY()) {
			this.broadcast(new EntityReachedPortalEvent(this, event));
		}
	}

	@Override
	public void addListener(EventHandler<EntityReachedPortalEvent> eventHandler) {
		this.reachedListeners.add(eventHandler);
	}

	@Override
	public void removeListener(EventHandler<EntityReachedPortalEvent> eventHandler) {
		this.reachedListeners.remove(eventHandler);
	}
	
	private void broadcast(EntityReachedPortalEvent event) {
		this.reachedListeners.forEach(listener -> listener.handle(event));
	}

	@Override
	public void accept(EntityVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
