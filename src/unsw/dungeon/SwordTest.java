package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;

public class SwordTest {
	//Moving one square down into a sword and then into an enemy to kill
	@Test
	public void testArea() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 2, 3);
		boolean hasSword = false;
		assertEquals(hasSword, dungeon.hasItem(Item.SWORD), "Player has sword: " + hasSword);
		player.move(keycode);
		hasSword = true;
		assertEquals(hasSword, dungeon.hasItem(Item.KEY), "Player has key: " + hasSword);
		keycode = KeyCode.UP;
		player.move(keycode);
		player.move(keycode);
		keycode = KeyCode.RIGHT;
		player.move(keycode);
		keycode = KeyCode.UP;
		player.move(keycode);
		keycode = KeyCode.RIGHT;
		player.move(keycode);
		keycode = KeyCode.RIGHT;
		player.move(keycode);
		keycode = KeyCode.RIGHT;
		player.move(keycode);
	}
}
