package unsw.dungeon.goal;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class BasicGoal extends Goal {
	
	@Override
	public Iterator<Goal> iterator() {
		return Collections.emptyIterator();
	}
	
	@Override
	public boolean hasSubgoals() {
		return false;
	}
	
}
