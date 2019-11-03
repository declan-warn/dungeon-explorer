package unsw.dungeon;

public class SwitchActivationEvent implements Event {
	
	private FloorSwitch floorSwitch;
	
	public SwitchActivationEvent(FloorSwitch floorSwitch) {
		this.floorSwitch = floorSwitch;
	}
	
	public FloorSwitch getSwitch() {
		return this.floorSwitch;
	}
	
	public boolean isActivated() {
		return this.floorSwitch.isActivated();
	}
	
}
