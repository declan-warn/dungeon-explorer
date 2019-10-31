package unsw.dungeon;

import javafx.scene.image.Image;

public class Treasure extends CollectableEntity {

	public static int worth = 100;
	
	public static Image img = new Image("/gold_pile.png");
	
	public Treasure(int x, int y) {
		super(x, y);
	}

}
