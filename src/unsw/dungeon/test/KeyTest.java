package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;

public class KeyTest {
	//Moving one square up into a key
	@Test
	public void keyCollection() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.UP;
		Player player = new Player(dungeon, 3, 2);
		//Check player does not have a key
		boolean hasKey = false;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
		//Player moves into key
		player.move(keycode);
		//Check player now has key in the inventory
		hasKey = true;
		assertEquals(hasKey, dungeon.hasItem(Item.KEY), "Player has key: " + hasKey);
	}
	
	//Test for multiple key collections but idk how?
}
