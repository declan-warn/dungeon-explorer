package unsw.dungeon;

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
		if (this.ticksLeft > 0) {
			this.dungeon.getPlayer().setInvincible(true);
		} else {
			this.dungeon.getPlayer().setInvincible(false);
		}
	}

}
