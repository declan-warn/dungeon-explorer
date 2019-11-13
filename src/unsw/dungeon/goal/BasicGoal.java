package unsw.dungeon.goal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class BasicGoal extends Goal {
	
	@Override
	public Iterator<Goal> iterator() {
		Goal goal = this;
		return new Iterator<Goal>() {
			private boolean hasYielded = false;
			
			@Override
			public boolean hasNext() {
				return !hasYielded;
			}
			
			@Override
			public Goal next() {
				if (!hasNext()) {
                    throw new NoSuchElementException();
                }
				hasYielded = true;
                return goal;
			}
		};
	}
	
	@Override
	public boolean hasSubgoals() {
		return false;
	}
	
}
