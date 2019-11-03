package unsw.dungeon;

import javafx.scene.image.Image;

public class Sword extends CollectableEntity {

	public static Image img = new Image("/greatsword_1_new.png");
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
	}

	@Override
	public void accept(EntityVisitor visitor) {
		visitor.visit(this);
	}
}
