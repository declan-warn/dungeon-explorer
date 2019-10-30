package unsw.dungeon;

public interface EventHandler<EventType> {

	public void handle(EventType event);
	
}
