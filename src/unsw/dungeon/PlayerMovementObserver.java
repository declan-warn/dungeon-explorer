package unsw.dungeon;

public interface PlayerMovementObserver extends Observer {

	public void update(PlayerMovementEvent e);
	
}
