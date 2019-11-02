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
    private Inventory inventory;
    private int score;
    private PortalNetwork portalNetwork;
    private PhysicsManager physics;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.inventory = new Inventory();
        this.score = 0;
        this.portalNetwork = new PortalNetwork();
        this.physics = new PhysicsManager();
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
    
    public void giveItem(CollectableEntity item) {
    	this.inventory.add(item);
    }
    
    public void takeItem(CollectableEntity item) {
    	this.inventory.remove(item);
    }
    
    public boolean hasItem(Item itemType) {
    	return this.inventory.contains(itemType);
    }
    
    public CollectableEntity getItem(Item itemType) {
    	return this.inventory.get(itemType);
    }
    
    public void onDungeonLoad() {
    	entities.forEach(entity -> {
    		if (entity != null) {
    			entity.onDungeonLoad(this);
    		} 	
    	});
    	
    	this.getPlayer().onMovement((event) -> this.tick());
    	
    	this.physics.onDungeonLoad(this);
    }
    
    public List<Boulder> getBoulders() {
    	List<Boulder> boulders = new ArrayList<>();
    	entities.forEach(entity -> {
    		if (entity.getEntityType().equals("Boulder")) {
    			boulders.add((Boulder) entity);
    		} 	
    	});
    	return boulders;
    }
    
    public void visit(CollectableEntity collectable) {
    	//System.out.println("COLLECTABLE: " + collectable.getType().toString());
    }
    
    public void visit(Treasure treasure) {
    	treasure.addListener(new EventHandler<ItemPickupEvent>() {
			@Override
			public void handle(ItemPickupEvent event) {
				score += Treasure.worth;
				System.out.println("SCORE = " + score);
			}
    	});
    }
    
    public void registerPortal(Portal portal) {
    	this.portalNetwork.register(portal);
    }
    
    public void tick() {
    	this.entities.forEach(Entity::tick);
    }
    
    public void registerMovable(Movable movable) {
    	this.physics.addMovable(movable);
    }
    
    public void registerMovementHandler(EventHandler<MovementEvent> handler) {
    	this.physics.addHandler(handler);
    }
    
}
