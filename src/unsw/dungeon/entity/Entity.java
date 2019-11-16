package unsw.dungeon.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.event.EventListener;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity implements EventListener, Displayable {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    
    protected Dungeon dungeon;

	private String type;
	
//	protected ObjectProperty<Image> imageProperty;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type;
//        this.imageProperty = new SimpleObjectProperty<>();
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
	
	public void tick() {}
	
	public void kill() {}
    
    public void broadcast(unsw.dungeon.event.Event event) {
    	this.dungeon.broadcastEvent(event);
    }
    
}
