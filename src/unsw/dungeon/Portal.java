package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;

public class Portal extends Entity implements EventHandler<MovementEvent>, EventEmitter<PlayerReachedPortalEvent> {
	
	public static Image img = new Image("/portal.png");
	
	private int linkId;
	private Set<EventHandler<PlayerReachedPortalEvent>> reachedListeners;

	public Portal(int x, int y, int linkId) {
		super(x, y);
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
			this.broadcast(new PlayerReachedPortalEvent(this, event));
		}
	}

	@Override
	public void addListener(EventHandler<PlayerReachedPortalEvent> eventHandler) {
		this.reachedListeners.add(eventHandler);
	}

	@Override
	public void removeListener(EventHandler<PlayerReachedPortalEvent> eventHandler) {
		this.reachedListeners.remove(eventHandler);
	}
	
	private void broadcast(PlayerReachedPortalEvent event) {
		this.reachedListeners.forEach(listener -> listener.handle(event));
	}

}
