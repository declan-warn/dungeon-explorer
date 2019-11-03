package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;

public class KeyTest {
	//Moving one square up into a key
	@Test
	public void testArea() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.UP;
		Player player = new Player(dungeon, 3, 2);
		boolean hasKey = false;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
		player.move(keycode);
		hasKey = true;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
	}
}
