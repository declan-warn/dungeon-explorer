package unsw.dungeon.event;

public interface Event {

	public void accept(EventListener listener);
	
}
