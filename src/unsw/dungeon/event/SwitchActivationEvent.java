package unsw.dungeon.event;

import unsw.dungeon.entity.FloorSwitch;

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

	@Override
	public void accept(EventListener listener) {
		listener.handle(this);
	}
	
}
