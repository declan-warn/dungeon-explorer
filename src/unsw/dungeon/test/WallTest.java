package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;

public class WallTest {
	//Moving one square to the left wall should prevent movement 
	@Test
	public void singleMoveIntoWall() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.LEFT;
		Player player = new Player(dungeon, 1, 2);
		player.onDungeonLoad(dungeon);
		player.move(keycode);
		int answerX = 1;
		int answerY = 2;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
	
	//Moving multiple squares to the left wall should prevent movement
	@Test
	void multipleMoveIntoWall() throws FileNotFoundException{
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.LEFT;
		Player player = new Player(dungeon, 1, 2);
		player.onDungeonLoad(dungeon);
		for (int i = 0; i < 10; i++) {
			player.move(keycode);
			int answerX = 1;
			int answerY = 2;
			assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
			assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		}
	}
}
