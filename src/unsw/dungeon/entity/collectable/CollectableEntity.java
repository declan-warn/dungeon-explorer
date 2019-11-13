package unsw.dungeon.entity.collectable;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.event.ItemPickupEvent;
import unsw.dungeon.event.MovementEvent;

public abstract class CollectableEntity extends Entity {
	
	public CollectableEntity(int x, int y, String type) {
		super(x, y, type);
	}
	
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		this.accept(dungeon);
	}
	
	public abstract void accept(Dungeon dungeon);
	
	@Override
	public void handle(MovementEvent event) {
		if (event.wouldCollide(this) && event.isPlayer()) {
			event.andThen((e) -> {
				if (this.getType().equals(Item.SWORD) && dungeon.hasItem(Item.SWORD)) {
					return;
				} else {
					this.x().set(this.dungeon.getWidth());
					this.dungeon.giveItem(this);
//					this.broadcast(new ItemPickupEvent(this));
					this.pickup();
				}
			});
		}
	}
	
	public abstract Item getType();
	
	public boolean isType(Item itemType) {
		return this.getType().equals(itemType);
	}

	public boolean isActive() { return false; }
	
	public void pickup() {
		this.broadcast(new ItemPickupEvent(this));
	}

}