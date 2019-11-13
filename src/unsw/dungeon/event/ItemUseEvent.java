package unsw.dungeon.event;

import unsw.dungeon.entity.collectable.CollectableEntity;
import unsw.dungeon.entity.collectable.Item;

public class ItemUseEvent implements Event {
	
	private CollectableEntity item;
	private Item type;
	private int remaining_uses;
	
	public ItemUseEvent(CollectableEntity item, Item type, int remaining_uses) {
		this.item = item;
		this.type = type;
		this.remaining_uses = remaining_uses;
	}
	
	public ItemUseEvent(CollectableEntity item, Item type) {
		this(item, type, 0);
	}

	public CollectableEntity getItem() {
		return this.item;
	}
	
	public boolean isItem(CollectableEntity item) {
		return this.item == item;
	}
	
	public Item getType() {
		return this.type;
	}
	
	public boolean isType(Item type) {
		return this.type == type;
	}
	
	public int getRemainingUses() {
		return this.remaining_uses;
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}

}
