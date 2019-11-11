package unsw.dungeon.event;

import unsw.dungeon.entity.collectable.CollectableEntity;
import unsw.dungeon.entity.collectable.Item;

public class ItemPickupEvent implements Event {
	
	private CollectableEntity item;
	
	public ItemPickupEvent(CollectableEntity item) {
		this.item = item;
	}
	
	public Item getType() {
		return this.item.getType();
	}
	
	public boolean isType(Item type) {
		return this.getType().equals(type);
	}
	
	public boolean isItemInstance(CollectableEntity item) {
		return this.item == item;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}

}
