package unsw.dungeon.test;

import java.io.FileNotFoundException;

import unsw.dungeon.DungeonLoader;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Door;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Exit;
import unsw.dungeon.entity.FloorSwitch;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.entity.collectable.Key;
import unsw.dungeon.entity.collectable.Potion;
import unsw.dungeon.entity.collectable.Sword;
import unsw.dungeon.entity.collectable.Treasure;

public class DungeonTestLoader extends DungeonLoader {

	public DungeonTestLoader(String filename) throws FileNotFoundException {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLoad(Entity player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Wall wall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Exit exit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Key key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Door door) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Treasure treasure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Portal portal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Boulder boulder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(FloorSwitch floorswitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Enemy enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Sword sword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Potion potion) {
		// TODO Auto-generated method stub
		
	}

}
