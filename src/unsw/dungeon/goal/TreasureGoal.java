package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.collectable.Item;
import unsw.dungeon.event.GoalCompletionEvent;
import unsw.dungeon.event.ItemPickupEvent;

public class TreasureGoal extends BasicGoal {
	
	private int treasureTotal;
	private int treasureCollected;

	public TreasureGoal() {
		super();
		this.treasureTotal = 0;
		this.treasureCollected = 0;
	}	
	
	@Override
	public boolean isComplete() {
		return this.treasureCollected == this.treasureTotal;
	}

	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		super.onDungeonLoad(dungeon);
		this.treasureTotal = dungeon.getEntitiesOfType("Treasure").size();
	}
	
	@Override
	public void handle(ItemPickupEvent event) {
		if (event.isType(Item.TREASURE)) {
			this.treasureCollected++;
			if (this.isComplete()) {
				System.out.println("ALL TREASURE COLLECTED");
				this.broadcast(new GoalCompletionEvent(this));
			}
		}
	}
	
}
