package unsw.dungeon.entity.collectable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.event.ItemUseEvent;

public class Sword extends CollectableEntity {

	public static Image img = new Image("/gold_sword.png");
	private int totalHitsLeft = 5;
	
	public Sword(int x, int y) {
		super(x, y, "Sword");
	}
	
	@Override
	public Item getType() {
		return Item.SWORD;
	}
	
	@Override
	public void accept(Dungeon dungeon) {
		dungeon.visit(this);
	}
	
	public int gettotalHitsLeft() {
		return this.totalHitsLeft;
	}
	
	public void decreasetotalHitsLeft() {
		totalHitsLeft = totalHitsLeft - 1;
		this.dungeon.broadcastEvent(new ItemUseEvent(Item.SWORD, totalHitsLeft));
	}
	
}
