package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityVisitor;
import unsw.dungeon.EventHandler;
import unsw.dungeon.ItemPickupEvent;
import unsw.dungeon.Treasure;

public class TreasureGoal implements BasicGoal, EntityVisitor {
	
	private int treasureTotal;
	private int treasureCollected;

	public TreasureGoal() {
		this.treasureTotal = 0;
		this.treasureCollected = 0;
	}	
	
	@Override
	public boolean isComplete() {
		return this.treasureCollected == this.treasureTotal;
	}

	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		this.treasureTotal = dungeon.getEntitiesOfType("Treasure").size();
	}
	
	@Override
	public void visit(Treasure treasure) {
		treasure.addListener(new EventHandler<ItemPickupEvent>() {
			@Override
			public void handle(ItemPickupEvent event) {
				treasureCollected++;
				if (isComplete()) {
					// TODO: do something
					System.out.println("All treasure collected");
				}
			}
		});
	}

}
