package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    
    protected Dungeon dungeon;

	private String type;


    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public void setPos(int x, int y) {
    	this.x().set(x);
    	this.y().set(y);
	}

    public void setX(int newValue) {
        x().set(newValue);
    }
    
    public void setY(int newValue) {
        y().set(newValue);
    }
    
    public void onDungeonLoad(Dungeon dungeon) {
    	this.dungeon = dungeon;
    }

	public String getEntityType() {
		return this.type;
	}
    
}
