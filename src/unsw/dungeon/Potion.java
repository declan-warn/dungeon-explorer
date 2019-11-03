package unsw.dungeon;

import javafx.scene.image.Image;

public class Potion extends CollectableEntity {
	
	public static Image img = new Image("/bubbly.png");

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
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	protected void broadcast(ItemPickupEvent event) {
		super.broadcast(event);
		this.ticksLeft = 10;
	}
	
	@Override
	public boolean isActive() {
		return this.ticksLeft > 0; 
	}
	
	@Override
	public void tick() {
		super.tick();
		this.ticksLeft--;
		if (this.ticksLeft > 0) {
			this.dungeon.getPlayer().setInvincible(true);
		} else {
			this.dungeon.getPlayer().setInvincible(false);
		}
	}

}
