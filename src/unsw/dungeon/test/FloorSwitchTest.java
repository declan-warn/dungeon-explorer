package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;

public class FloorSwitchTest {
	//Push boulder onto a floor switch activating it
	@Test
	public void BoulderOntoSwitch() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 2, 4);
		
		//Move down 3 times pushing the boulder onto the floor switch
		player.move(keycode);
		player.move(keycode);
		player.move(keycode);
		int answerX = 2;
		int answerY = 7;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		
		//Check that the floor switch is activated
	}
}
