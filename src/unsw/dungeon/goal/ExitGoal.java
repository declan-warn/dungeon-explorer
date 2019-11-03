package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityVisitor;
import unsw.dungeon.EventHandler;
import unsw.dungeon.Exit;
import unsw.dungeon.PlayerReachedExitEvent;

public class ExitGoal extends BasicGoal implements EntityVisitor {
	
	private boolean isComplete = false;

	@Override
	public boolean isComplete() {
		return this.isComplete;
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
		Goal goal = this;
		exit.addListener(new EventHandler<PlayerReachedExitEvent>() {
			@Override
			public void handle(PlayerReachedExitEvent event) {
				isComplete = event.isAtExit();
				if (isComplete()) {
					// TODO: do something
					System.out.println("DONE EXIT GOAL");
					broadcast(new GoalCompletionEvent(goal));
				}
			}
		});
	}

}
