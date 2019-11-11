package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;

public class WallTest {
	//Moving one square to the left wall should prevent movement 
	@Test
	public void testArea() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
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
	void testArea2() throws FileNotFoundException{
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
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
