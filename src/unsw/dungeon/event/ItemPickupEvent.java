package unsw.dungeon.event;

import unsw.dungeon.entity.collectable.CollectableEntity;
import unsw.dungeon.entity.collectable.Item;

public class ItemPickupEvent implements Event {
	
	private CollectableEntity item;
	private int remainingUses;
	
	public ItemPickupEvent(CollectableEntity item, int remainingUses) {
		this.item = item;
		this.remainingUses = remainingUses;
	}
	
	public ItemPickupEvent(CollectableEntity item) {
		this(item, 1);
	}
	
	public Item getType() {
		return this.item.getType();
	}
	
	public boolean isType(Item type) {
		return this.getType().equals(type);
	}
	
	public CollectableEntity getItem() {
		return this.item;
	}
	
	public boolean isItemInstance(CollectableEntity item) {
		return this.item == item;
	}
	
	public int getRemainingUses() {
		return this.remainingUses;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}

}
