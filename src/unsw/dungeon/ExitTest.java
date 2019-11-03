package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;

public class ExitTest {
	//Moving one square down into an exit
	@Test
	public void testArea() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 18, 15);
		dungeon.registerMovable(player);
		player.move(keycode);
		int answerX = 18;
		int answerY = 16;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
}
