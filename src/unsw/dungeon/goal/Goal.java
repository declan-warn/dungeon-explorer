package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EventEmitter;
import unsw.dungeon.EventHandler;

public abstract class Goal implements EventEmitter<GoalCompletionEvent> {
	
	protected Set<EventHandler<GoalCompletionEvent>> completionListeners;
	
	public Goal() {
		this.completionListeners = new HashSet<>();
	}
	
	public void addListener(EventHandler<GoalCompletionEvent> eventHandler) {
		this.completionListeners.add(eventHandler);
	}
	
	public void removeListener(EventHandler<GoalCompletionEvent> eventHandler) {
		this.completionListeners.remove(eventHandler);
	}
	
	public abstract boolean isComplete();
	
	public abstract void onDungeonLoad(Dungeon dungeon);
	
	public void broadcast(GoalCompletionEvent event) {
		this.completionListeners.forEach(listener -> listener.handle(event));
	}
	
}
