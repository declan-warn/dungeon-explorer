package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;

public class PortalTest {
	//Moving one square down into a portal
	@Test
	public void portalTransportation() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		//Player spawns at (1,7) one square up from a portal
		Player player = new Player(dungeon, 1, 7);
		
		//Player moves down into the portal
		player.move(keycode);
		
		//Asserts that player has ended up at (4,8) at the connected portal and is not 
		//Teleported back
		int answerX = 4;
		int answerY = 8;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		
		//Player moves up then down again ending up at the original portal
		keycode = KeyCode.DOWN;
		player.move(keycode);
		keycode = KeyCode.UP;
		player.move(keycode);

		answerX = 1;
		answerY = 8;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
}
