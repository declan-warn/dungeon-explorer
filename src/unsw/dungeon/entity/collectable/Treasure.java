package unsw.dungeon.entity.collectable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;

public class Treasure extends CollectableEntity {
	
	public static int worth = 100;
	
	public Treasure(int x, int y) {
		super(x, y, "Treasure");
	}
	
	@Override
	public Item getType() {
		return Item.TREASURE;
	}

	@Override
	public Image getImage() {
		return new Image("/gold_ingot.png");
	}

}
