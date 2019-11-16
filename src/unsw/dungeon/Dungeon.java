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
import unsw.dungeon.entity.collectable.Treasure;
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
public class Dungeon implements EventListener {

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
    	entities.forEach(entity -> {if (entity != null) entity.onDungeonLoad(this);});
    	
    	if (this.goal != null)
    		this.goal.onDungeonLoad(this);
    }
    
    @Override
    public void handle(MovementEvent event) {
    	if (event.isPlayer()) {
    		event.andThen(e -> {
    			if (!e.isCancelled()) {
    				player.setX(e.getX());
    				player.setY(e.getY());
    				this.tick();
    			}
    		});
    	}
    }
    
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
    
    public Goal getGoal() {
    	return this.goal;
    }
    
    @Override
    public void handle(GoalCompletionEvent event) {
    	this.score += Treasure.worth;
    	
    	if (event.getGoal() == this.goal) {
    		System.out.println("DUNGEON GOAL COMPLETE");
    		this.exit(ExitStatus.SUCCESS);
    	}
    }
    
    @Override
    public void handle(ItemPickupEvent event) {
    	if (event.isType(Item.TREASURE)) {
    		this.score += Treasure.worth;
    	}
    }
    
    public int getScore() {
    	return this.score;
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
