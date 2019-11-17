package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;
import unsw.dungeon.entity.collectable.Sword;

public class SwordTest {
	//Moving one square down into a sword and then into an enemy to kill
	@Test
	public void SwordCollectionKill() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 2, 3);
		
		//Asserts that a sword isn't in the players inventory
		boolean hasSword = false;
		assertEquals(hasSword, dungeon.hasItem(Item.SWORD), "Player has sword: " + hasSword);
		
		//Move into a sword to collect the sword
		player.move(keycode);
		
		//Asserts that a sword is in the players inventory
		hasSword = true;
		assertEquals(hasSword, dungeon.hasItem(Item.SWORD), "Player has key: " + hasSword);
		
		//Moves into the enemy and keeps moving to ensure that player has survived
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
		keycode = KeyCode.RIGHT;
		player.move(keycode);
		keycode = KeyCode.RIGHT;
		player.move(keycode);
		keycode = KeyCode.RIGHT;
		player.move(keycode);
		keycode = KeyCode.RIGHT;
		player.move(keycode);
		
		Sword sword = (Sword) dungeon.getItem(Item.SWORD);
		int hitsLeft = 4;
		assertEquals(hitsLeft, sword.gettotalHitsLeft(), "Sword has hits left: " + hitsLeft);
	}
}
