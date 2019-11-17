package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;

public class EnemyTest {
	//Moving one square down into an enemy (kills player)
	@Test
	public void MoveIntoEnemy() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.RIGHT;
		Player player = new Player(dungeon, 13, 1);
		player.move(keycode);
		
		//pLAYER SHOUld die here
		int answerX = 14;
		int answerY = 1;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}	
}
