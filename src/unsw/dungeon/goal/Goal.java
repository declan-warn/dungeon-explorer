package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;

public interface Goal {

	public boolean isComplete();
	
	public void onDungeonLoad(Dungeon dungeon);
	
}
