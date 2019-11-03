package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityVisitor;
import unsw.dungeon.EventHandler;
import unsw.dungeon.FloorSwitch;

public class BouldersGoal extends BasicGoal implements EntityVisitor {
	
	private Set<FloorSwitch> switches;
	
	public BouldersGoal() {
		super();
		this.switches = new HashSet<>();
	}

	@Override
	public boolean isComplete() {
		return this.switches.stream().allMatch(FloorSwitch::isActivated);
	}
	
	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		dungeon
			.getEntitiesOfType("FloorSwitch")
			.forEach(floorSwitch -> floorSwitch.accept(this));
	}
	
	@Override
	public void visit(FloorSwitch floorSwitch) {
		this.switches.add(floorSwitch);
		floorSwitch.addListener(event -> {
			if (this.isComplete()) {
				// TODO: something else
				System.out.println("ALL SWITCHES ACTIVE");
				this.broadcast(new GoalCompletionEvent(this));
			}
		});
	}

}
