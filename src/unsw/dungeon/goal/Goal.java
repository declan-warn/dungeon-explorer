package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.GoalCompletionEvent;

public abstract class Goal implements EventListener, Iterable<Goal> {
	
	protected Dungeon dungeon;
	
	public abstract boolean isComplete();
	
	public void onDungeonLoad(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.dungeon.registerListener(this);
	}
	
	public void broadcast(GoalCompletionEvent event) {
		this.dungeon.broadcastEvent(event);
	}
	
}
