package unsw.dungeon.event;

import unsw.dungeon.Item;

public class ItemPickupEvent implements Event {
	
	private Item type;
	
	public ItemPickupEvent(Item type) {
		this.type = type;
	}
	
	public Item getType() {
		return this.type;
	}
	
	public boolean isType(Item type) {
		return this.type.equals(type);
	}

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}

}
