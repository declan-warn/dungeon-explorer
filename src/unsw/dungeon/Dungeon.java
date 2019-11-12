/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.ExitStatus;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.PortalNetwork;
import unsw.dungeon.entity.collectable.CollectableEntity;
import unsw.dungeon.entity.collectable.Inventory;
import unsw.dungeon.entity.collectable.Item;
import unsw.dungeon.event.Event;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.EventManager;
import unsw.dungeon.event.GoalCompletionEvent;
import unsw.dungeon.event.ItemPickupEvent;
import unsw.dungeon.event.MovementEvent;
import unsw.dungeon.goal.Goal;
import unsw.dungeon.goal.GoalSystem;
import unsw.dungeon.util.SimpleObservable;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon extends SimpleObservable<String> implements EventListener {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Inventory inventory;
    private int score;
    private PortalNetwork portalNetwork;
    private Goal goal;
    private EventManager events;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.inventory = new Inventory();
        this.score = 0;
        this.portalNetwork = new PortalNetwork();
        this.events = new EventManager();
        
        this.events.addListener(this);
        this.events.addListener(this.portalNetwork);
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
    	if (entity != null) {
    		entities.add(entity);
    		this.events.addListener(entity);
    	}
    }
    
    public void giveItem(CollectableEntity item) {
    	this.inventory.add(item);
    	if (item.isType(Item.KEY)) {
    		this.notify("has_key=true");
    	} else if (item.isType(Item.SWORD)) {
    		this.notify("has_sword=true");
    	}
    }
    
    public void takeItem(CollectableEntity item) {
    	this.inventory.remove(item);
    	if (item.isType(Item.KEY)) { 
    		this.notify("has_key=false");
    	} else if (item.isType(Item.SWORD)) {
    		this.notify("has_sword=false");
    	}
    }
    
    public boolean hasItem(Item itemType) {
    	return this.inventory.contains(itemType);
    }
    
    public CollectableEntity getItem(Item itemType) {
    	return this.inventory.get(itemType);
    }
    
    public void onDungeonLoad() {
    	entities.forEach(entity -> {if (entity != null) entity.onDungeonLoad(this);});
    	
    	if (this.goal != null)
    		this.goal.onDungeonLoad(this);
    }
    
    @Override
    public void handle(MovementEvent event) {
    	if (event.isPlayer()) {
    		this.tick();
    	}
    }
    
    public void visit(CollectableEntity collectable) {
    	//System.out.println("COLLECTABLE: " + collectable.getType().toString());
    }
    
//    public void visit(Treasure treasure) {
//    	treasure.addListener(new EventHandler<ItemPickupEvent>() {
//			@Override
//			public void handle(ItemPickupEvent event) {
//				score += Treasure.worth;
//				System.out.println("SCORE = " + score);
//			}
//    	});
//    }
    
    public void registerPortal(Portal portal) {
    	this.portalNetwork.register(portal);
    }
    
    public void tick() {
    	this.entities.forEach(Entity::tick);
    }
    
    public void exit(ExitStatus status) {
    	System.out.println("DUNGEON STATUS: " + status);
    }
    
    public void setGoal(Goal goal) {
    	this.goal = goal;
    }
    
    @Override
    public void handle(GoalCompletionEvent event) {
    	if (event.getGoal() == this.goal) {
    		System.out.println("DUNGEON GOAL COMPLETE");
    		this.exit(ExitStatus.SUCCESS);
    	}
    }
    
    public List<Entity> getEntitiesOfType(String type) {
    	return this.entities.stream()
    			.filter(entity -> entity.getEntityType().equals(type))
    			.collect(Collectors.toList());
    }
    
    public List<Entity> getEntities() {
    	return this.entities;
    }
    
    public void broadcastEvent(unsw.dungeon.event.Event event) {
    	this.events.broadcast(event);
    }

	public void registerListener(EventListener listener) {
		this.events.addListener(listener);		
	}
    
}
