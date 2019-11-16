package unsw.dungeon.entity.collectable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Paired;

public class Key extends CollectableEntity implements Paired {
	
	private int id;
	
	public Key(int x, int y, int id) {
		super(x, y, "Key");
		this.id = id;
	}

	@Override
	public Item getType() {
		return Item.KEY;
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public Image getImage() {
		return new Image("/new_key.png");
	}	

}
