package unsw.dungeon;

public interface EventEmitter<EventType> {
	
	public void addListener(EventHandler<EventType> eventHandler);
	
	public void removeListener(EventHandler<EventType> eventHandler);

}
