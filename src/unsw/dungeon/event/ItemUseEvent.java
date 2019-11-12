package unsw.dungeon.event;

import unsw.dungeon.entity.collectable.Item;

public class ItemUseEvent implements Event {
	
	private Item type;
	private int remaining_uses;
	
	public ItemUseEvent(Item type, int remaining_uses) {
		this.type = type;
		this.remaining_uses = remaining_uses;
	}
	
	public ItemUseEvent(Item type) {
		this(type, 0);
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
