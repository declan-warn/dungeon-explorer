/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Inventory<CollectableEntity> inventory;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.inventory = new Inventory<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void onDungeonLoad() {
    	entities.forEach(entity -> {if (entity != null) entity.onDungeonLoad(this);});
    }
    
    public void giveItem(CollectableEntity item) {
    	this.inventory.add(item);
    }
    
    public void takeItem(CollectableEntity item) {
    	this.inventory.remove(item);
    }
    
    public boolean hasItem(Class<? extends CollectableEntity> itemType) {
    	return this.inventory.contains(itemType);
    }
    
}
