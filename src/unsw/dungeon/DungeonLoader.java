package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Door;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Exit;
import unsw.dungeon.entity.FloorSwitch;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.entity.collectable.Key;
import unsw.dungeon.entity.collectable.Potion;
import unsw.dungeon.entity.collectable.Sword;
import unsw.dungeon.entity.collectable.Treasure;
import unsw.dungeon.goal.BouldersGoal;
import unsw.dungeon.goal.ComplexGoal;
import unsw.dungeon.goal.EnemiesGoal;
import unsw.dungeon.goal.ExitGoal;
import unsw.dungeon.goal.Goal;
import unsw.dungeon.goal.TreasureGoal;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoal = json.getJSONObject("goal-condition");
        Goal dungeonGoal = loadGoal(jsonGoal);
        
        dungeon.setGoal(dungeonGoal);
        
        dungeon.onDungeonLoad();
        
        return dungeon;
    }

    private Goal loadGoal(JSONObject json) {
    	String type = json.getString("goal");
    	
    	Goal goal = null;
    	
    	switch (type) {
    	case "enemies":
    		goal = new EnemiesGoal();
    		break;
    		
    	case "treasure":
    		goal = new TreasureGoal();
    		break;
    		
    	case "exit":
    		goal = new ExitGoal();
    		break;
    		
    	case "boulders":
    		goal = new BouldersGoal();
    		break;
    		
    	case "AND":
    		goal = loadSubgoals(json, ComplexGoal.allRequired());
    		break;
    		
    	case "OR":
    		goal = loadSubgoals(json, ComplexGoal.someRequired());
    		break;
    	}
    	
    	return goal;
    }
    
    private Goal loadSubgoals(JSONObject json, ComplexGoal goal) {
    	JSONArray jsonSubgoals = json.getJSONArray("subgoals");
    	for (int i = 0; i < jsonSubgoals.length(); i++) {
    		JSONObject jsonSubgoal = jsonSubgoals.getJSONObject(i);
    		Goal subgoal = this.loadGoal(jsonSubgoal);
    		goal.add(subgoal);
    	}
		return goal;	
    }

	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        int id = -1;
        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "key":
        	id = json.getInt("id");
        	Key key = new Key(x, y, id);
        	onLoad(key);
        	entity = key;
        	break;
        case "door":
        	id = json.getInt("id");
        	Door door = new Door(x, y, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "portal":
        	id = json.getInt("id");
        	Portal portal = new Portal(x, y, id);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;    
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "floorswitch":
        	FloorSwitch floorswitch = new FloorSwitch(x, y);
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x, y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "potion":
        	Potion potion = new Potion(x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
    	// TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch floorswitch);

    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Potion potion);
}
