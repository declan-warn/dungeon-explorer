package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public abstract class CollectableEntity extends Entity implements EventHandler<MovementEvent>, EventEmitter<ItemPickupEvent> {

	private Set<EventHandler<ItemPickupEvent>> pickupListeners;
	
	public CollectableEntity(int x, int y) {
		super(x, y);
		this.pickupListeners = new HashSet<>();
	}
	
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		dungeon.getPlayer().onMovement(this);
	}
	
	@Override
	public void handle(MovementEvent event) {
		if (event.getX() == getX() && event.getY() == getY()) {
			// TODO: find a better way to hide the entity
			this.x().set(this.dungeon.getWidth());
			
			this.dungeon.giveItem(this);
			
			this.broadcast(new ItemPickupEvent(this.getType()));
		}
	}
	
	public abstract Item getType();
	
	public boolean isType(Item itemType) {
		return this.getType().equals(itemType);
	}
	
	@Override
	public void addListener(EventHandler<ItemPickupEvent> eventHandler) {
		this.pickupListeners.add(eventHandler);
	}
	
	@Override
	public void removeListener(EventHandler<ItemPickupEvent> eventHandler) {
		this.pickupListeners.remove(eventHandler);
	}
	
	protected void broadcast(ItemPickupEvent event) {
		this.pickupListeners.forEach(listener -> listener.handle(event));
	}

}
