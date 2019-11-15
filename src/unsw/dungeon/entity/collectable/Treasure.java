package unsw.dungeon.entity.collectable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;

public class Treasure extends CollectableEntity {

	//public static Image img = new Image("/gold_ingot.png");
	
	public static int worth = 100;
	
	public Treasure(int x, int y) {
		super(x, y, "Treasure");
	}
	
	@Override
	public Item getType() {
		return Item.TREASURE;
	}
	
	@Override
	public void accept(Dungeon dungeon) {
		dungeon.visit(this);
	}

}
