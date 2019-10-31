package unsw.dungeon;

public abstract class CollectableEntity extends Entity implements EventHandler<MovementEvent> {

	public CollectableEntity(int x, int y) {
		super(x, y);
	}
	
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		dungeon.getPlayer().onMovement(this);
	}
	
	@Override
	public void handle(MovementEvent event) {
		if (event.getX() == getX() && event.getY() == getY()) {
			// TODO: find a better way to hide the entity
			this.x().set(this.dungeon.getWidth());
			
			this.dungeon.giveItem(this);
		}
	}

}
