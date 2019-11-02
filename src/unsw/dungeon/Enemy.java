package unsw.dungeon;

import javafx.scene.image.Image;

public class Enemy extends Entity {
	
	public static Image img = new Image("/deep_elf_master_archer.png");

	public Enemy(int x, int y) {
		super(x, y, "Enemy");
	}

}
