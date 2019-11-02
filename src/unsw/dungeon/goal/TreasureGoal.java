package unsw.dungeon.goal;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
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
		List<Entity> treasure = dungeon.getEntitiesOfType("Treasure");
		this.treasureTotal = treasure.size();
		treasure.forEach(t -> t.accept(this));		
	}
	
	@Override
	public void visit(Treasure treasure) {
		System.out.println("??");
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
