package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javafx.scene.input.KeyCode;

public class MovementTest {
	//Moving one square to the right
	@Test
	public void testArea() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.RIGHT;
		Player player = new Player(dungeon, 1, 2);
		dungeon.registerMovable(player);
		player.move(keycode);
		int answerX = 2;
		int answerY = 2;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
	
	//Moving multiple squares to the right
	@Test
	void testArea2() throws FileNotFoundException{
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.RIGHT;
		Player player = new Player(dungeon, 3, 1);
		dungeon.registerMovable(player);
		int originalX = 4;
		for (int i = 0; i < 10; i++) {
			player.move(keycode);
			int answerX = originalX + i;
			int answerY = 1;
			assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
			assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		}
	}
}
