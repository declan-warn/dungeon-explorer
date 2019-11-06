package unsw.dungeon.event;

public interface EventListener {

	default public void handle(DoorChangeEvent event) {}
	
}
