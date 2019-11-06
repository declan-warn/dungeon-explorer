package unsw.dungeon.goal;

import java.util.HashSet;
import java.util.Set;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EventEmitter;
import unsw.dungeon.EventHandler;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.GoalCompletionEvent;

public abstract class Goal implements EventEmitter<GoalCompletionEvent>, EventListener {
	
	protected Dungeon dungeon;
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
	
	public void onDungeonLoad(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.dungeon.registerListener(this);
	}
	
	public void broadcast(GoalCompletionEvent event) {
		this.completionListeners.forEach(listener -> listener.handle(event));
		this.dungeon.broadcastEvent(event);
	}
	
}
