package unsw.dungeon.entity.collectable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.event.Event;
import unsw.dungeon.event.ItemPickupEvent;

public class Potion extends CollectableEntity {
	
	public static Image img = new Image("/experience_bottle.png");

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
			this.dungeon.getPlayer().setInvincible(true);
		} else {
			this.dungeon.getPlayer().setInvincible(false);
		}
	}

}
