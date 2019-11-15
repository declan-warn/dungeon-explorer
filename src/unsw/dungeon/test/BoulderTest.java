package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;

public class BoulderTest {
	//Moving one square down into a boulder
	@Test
	public void moveIntoBoulder() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 2, 2);
		
		//Move down into boulder and ensure player is able to move (boulder is not obstructed)
		player.move(keycode);
		int answerX = 2;
		int answerY = 3;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
	
	//Moving right into a boulder into a wall
	@Test
	public void moveBoulderWall() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.RIGHT;
		Player player = new Player(dungeon, 1, 3);
		
		//Player moves a boulder right into a wall (obstructed)
		player.move(keycode);
		
		//Asserts that since the boulder is obstructed player will not move (remains at (1,3))
		int answerX = 1;
		int answerY = 3;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
	
	//Moving a boulder into another boulder (block movement)
	@Test
	public void moveBoulderIntoBoulder() throws FileNotFoundException {
		DungeonTestLoader test = new DungeonTestLoader("maze.json");
		Dungeon dungeon = test.load();
		KeyCode keycode = KeyCode.DOWN;
		Player player = new Player(dungeon, 2, 2);
		
		//Player moves the first boulder down asserts player has moved from (2,2) to (2,3)
		player.move(keycode);
		int answerX = 2;
		int answerY = 3;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
		
		//Player moves once again however into 2 adjacent boulders thus obstructing movement
		//Remaining at (2,3) coordinate
		player.move(keycode);
		answerX = 2;
		answerY = 3;
		assertEquals(answerX, player.getX(), "new X coordinate is: " + answerX);
		assertEquals(answerY, player.getY(), "new Y coordinate is: " + answerY);
	}
}
