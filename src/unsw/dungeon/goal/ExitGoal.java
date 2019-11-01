package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityVisitor;
import unsw.dungeon.Exit;

public class ExitGoal implements BasicGoal, EntityVisitor {

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDungeonLoad(Dungeon dungeon) {
		for (Entity entity : dungeon.getEntities()) {
			if (entity.getEntityType().equals("Exit")) {
				entity.accept(this);
			}
		}
	}
	
	public void visit(Exit exit) {
		System.out.println("EXIT");
	}

}
