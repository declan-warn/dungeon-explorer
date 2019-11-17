package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;

public class ExitTest {
	//Moving one square down into an exit
	@Test
	public void moveIntoExit() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("testMaze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 18, 15);
		
		//Player moves down into exit 
		player.move(keycode);
		int answerX = 18;
		int answerY = 16;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
}
