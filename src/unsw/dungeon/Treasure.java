package unsw.dungeon;

import javafx.scene.image.Image;

public class Treasure extends CollectableEntity {

	public static Image img = new Image("/gold_pile.png");
	
	public static int worth = 100;
	
	public Treasure(int x, int y) {
		super(x, y);
	}
	
	@Override
	public Item getType() {
		return Item.TREASURE;
	}

}
