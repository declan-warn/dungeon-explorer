package unsw.dungeon.event;

public interface EventListener {

	default public void handle(DoorChangeEvent event) {}
	
	default public void handle(SwitchActivationEvent event) {}
	
	default public void handle(GoalCompletionEvent event) {}

	default public void handle(PlayerReachedExitEvent event) {}
	
}
