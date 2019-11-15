package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;

public class DoorTest {
	//Moving one square down into a door (block movement)
	@Test
	public void lockedDoorTest() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 3, 2);
		player.move(keycode);
		int answerX = 3;
		int answerY = 2;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
	
	//Moving one square up to get key and then down into a door opens door
	@Test
	public void collectKeyOpenDoor() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		
		//Collects key by moving one square up
		KeyCode keycode = KeyCode.UP;
		Player player = new Player(dungeon, 3, 2);
		player.move(keycode);
		
		//Asserts that player now holds the key
		boolean hasKey = true;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
		
		//Unlocks door at (3,2) and moves into (3,3) from previous test (3,2) blocked movement
		keycode = KeyCode.DOWN;
		player.move(keycode);
		player.move(keycode);
		int answerX = 3;
		int answerY = 3;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		
		//Ensures player no longer holds a key
		hasKey = false;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
		
		//Moves back up to show door is permenantly unlocked
		keycode = KeyCode.UP;
		player.move(keycode);
		answerX = 3;
		answerY = 2;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		
	}
	
	
}
