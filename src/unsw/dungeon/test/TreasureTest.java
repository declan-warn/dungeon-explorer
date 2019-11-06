package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;

public class TreasureTest {
	//Moving one square up into treasure 
	@Test
	public void testArea() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.UP;
		Player player = new Player(dungeon, 4, 4);
		boolean hasTreasure = false;
		assertEquals(hasTreasure, dungeon.hasItem(Item.TREASURE), "Player has treasure: " + hasTreasure);
		player.move(keycode);
		hasTreasure = true;
		assertEquals(hasTreasure, dungeon.hasItem(Item.TREASURE), "Player has treasure: " + hasTreasure);
	}
}
