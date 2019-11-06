package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.FloorSwitch;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.GoalCompletionEvent;
import unsw.dungeon.event.SwitchActivationEvent;

public class BouldersGoal extends BasicGoal {
	
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
		super.onDungeonLoad(dungeon);
		// TODO: try to do polymorphically instead of cast
		dungeon
			.getEntitiesOfType("FloorSwitch")
			.forEach(floorSwitch -> this.switches.add((FloorSwitch) floorSwitch));
	}
	
	@Override
	public void handle(SwitchActivationEvent event) {
		if (this.isComplete()) {
			System.out.println("All switches are now active.");
			this.broadcast(new GoalCompletionEvent(this));
		}
	}	

}
