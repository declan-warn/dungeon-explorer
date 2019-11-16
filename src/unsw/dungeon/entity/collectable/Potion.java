package unsw.dungeon.entity.collectable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.event.Event;
import unsw.dungeon.event.ItemPickupEvent;
import unsw.dungeon.event.ItemUseEvent;

public class Potion extends CollectableEntity {

	private int ticksLeft;
	
	public Potion(int x, int y) {
		super(x, y, "Potion");
		this.ticksLeft = 0;
	}

	@Override
	public void accept(Dungeon dungeon) {
		dungeon.visit(this);
	}

	@Override
	public Item getType() {
		return Item.POTION;
	}
	
	@Override
	public void handle(ItemPickupEvent event) {
		if (event.isItemInstance(this)) {
			this.ticksLeft = 10;
		}
	}
	
	@Override
	public boolean isActive() {
		return this.ticksLeft > 0; 
	}
	
	@Override
	public void tick() {
		super.tick();
		if (this.ticksLeft > 0) {
			System.out.println("Potion has " + this.ticksLeft + " ticks left");
			this.ticksLeft--;
			this.dungeon.broadcastEvent(new ItemUseEvent(this, Item.POTION, this.ticksLeft));
			this.dungeon.getPlayer().setInvincible(true);
		} else {
			this.dungeon.getPlayer().setInvincible(false);
		}
	}
	
	@Override
	public void pickup() {
		this.broadcast(new ItemPickupEvent(this, 10));
	}

	@Override
	public Image getImage() {
		return new Image("/experience_bottle.png");
	}

}
