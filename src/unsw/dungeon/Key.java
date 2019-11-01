package unsw.dungeon;

import javafx.scene.image.Image;

public class Key extends CollectableEntity {
	
	public static Image img = new Image("/key.png");
	
	public Key(int x, int y) {
		super(x, y, "Key");
	}

	@Override
	public Item getType() {
		return Item.KEY;
	}
	
	@Override
	public void accept(Dungeon dungeon) {
		dungeon.visit(this);
	}
	
	@Override
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}

}
