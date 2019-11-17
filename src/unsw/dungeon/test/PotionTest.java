package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;

public class PotionTest {

	public void PotionCollection() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 1, 1);
		
		//Asserts that a potion isn't in the players inventory
		boolean hasPotion = false;
		assertEquals(hasPotion, dungeon.hasItem(Item.POTION), "Player has potion: " + hasPotion);

		player.move(keycode);
		
		//Assert that player now has potion
		hasPotion = true;
		assertEquals(hasPotion, dungeon.hasItem(Item.POTION), "Player has potion: " + hasPotion);

		//Assert player is invincible
		boolean invinciblePlayer = true;
		assertEquals(invinciblePlayer, player.isInvincible(), "Player is invincible: " + invinciblePlayer);

		
	}
}
