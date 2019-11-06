package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Door;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Exit;
import unsw.dungeon.entity.FloorSwitch;
import unsw.dungeon.entity.Key;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Potion;
import unsw.dungeon.entity.Sword;
import unsw.dungeon.entity.Treasure;
import unsw.dungeon.entity.Wall;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(Exit.img);
    	addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(Key.img);
    	addEntity(key, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(door.getImage());
    	// TODO: Add a better way to change the sprite
//    	door.addListener(event -> {
//    		view.setImage(door.getImage());
//    	});
    	addEntity(door, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(Treasure.img);
    	addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(Portal.img);
    	addEntity(portal, view);
    }
    	
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(Boulder.img);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(FloorSwitch floorswitch) {
    	ImageView view = new ImageView(FloorSwitch.img);
    	addEntity(floorswitch, view);	
    }

    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(Enemy.img);
    	addEntity(enemy, view);
    }
    
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(Sword.img);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
    	ImageView view = new ImageView(Potion.img);
    	addEntity(potion, view);
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }




}
