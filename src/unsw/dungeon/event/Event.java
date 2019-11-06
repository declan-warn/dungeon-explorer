package unsw.dungeon.event;

public interface Event {

	public void accept(EventListener listener);
	
	default public void triggerEffects() {};
	
	default public boolean isCancelled() { return false; };
	
}
