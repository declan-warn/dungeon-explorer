package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityVisitor;
import unsw.dungeon.FloorSwitch;

public class BouldersGoal implements BasicGoal, EntityVisitor {
	
	private Set<FloorSwitch> switches;
	
	public BouldersGoal() {
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
	}

}
