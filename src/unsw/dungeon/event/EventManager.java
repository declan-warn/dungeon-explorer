package unsw.dungeon.event;

import java.util.HashSet;
import java.util.Set;

public class EventManager {

	Set<EventListener> listeners;
	
	public EventManager() {
		this.listeners = new HashSet<>();
	}
	
	public void addListener(EventListener listener) {
		this.listeners.add(listener);
	}
	
	public void broadcast(Event event) {
		this.listeners.forEach(event::accept);
		if (!event.isCancelled()) {
			event.triggerEffects();
		}
	}
	
}
