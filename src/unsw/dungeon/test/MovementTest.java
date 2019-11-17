package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;

public class MovementTest {
	//Moving one square to the right
	@Test
	public void singleMovement() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.RIGHT;
		Player player = new Player(dungeon, 1, 2);
		player.move(keycode);
		int answerX = 2;
		int answerY = 2;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
	
	//Moving multiple squares to the right
	@Test
	void multipleMovements() throws FileNotFoundException{
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.RIGHT;
		Player player = new Player(dungeon, 3, 1);
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
