package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;

public class DoorTest {
	//Moving one square down into a door (block movement)
	@Test
	public void testArea() throws FileNotFoundException {
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
	public void testArea2() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.UP;
		Player player = new Player(dungeon, 3, 2);
		player.move(keycode);
		keycode = KeyCode.DOWN;
		player.move(keycode);
		boolean hasKey = true;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
		player.move(keycode);
		int answerX = 3;
		int answerY = 3;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		hasKey = false;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
	}
	
	
}
