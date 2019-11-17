package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;

public class TreasureTest {
	//Moving one square up into treasure 
	@Test
	public void treasureCollection() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.UP;
		Player player = new Player(dungeon, 4, 4);
		
		//Asserts that player does not hold treasure
		boolean hasTreasure = false;
		assertEquals(hasTreasure, dungeon.hasItem(Item.TREASURE), "Player has treasure: " + hasTreasure);
		
		//Player moves up into treasure
		player.move(keycode);
		
		//Asserts that player holds a treasure after moving
		hasTreasure = true;
		assertEquals(hasTreasure, dungeon.hasItem(Item.TREASURE), "Player has treasure: " + hasTreasure);
	}
}
