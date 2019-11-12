package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;
import unsw.dungeon.entity.collectable.Key;
import unsw.dungeon.entity.collectable.Sword;
import unsw.dungeon.event.ItemPickupEvent;
import unsw.dungeon.menu.Controller;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends Controller {

    @FXML
    private GridPane squares;
    
    @FXML
    private VBox sidebar;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
//        Label keyIndicator = new Label("Holding key: " + dungeon.hasItem(Item.KEY));
        
        sidebar.setPadding(new Insets(16));
        sidebar.setAlignment(Pos.TOP_CENTER);
        
        DropShadow shadow = new DropShadow();
        shadow.setColor(new Color(0, 0, 0, 0.25));
        shadow.setRadius(2);
        shadow.setSpread(1);
        
        ColorAdjust greyscale = new ColorAdjust();
        greyscale.setInput(shadow);
        greyscale.setHue(-1);
        
        ImageView keyIndicator = new ImageView(Key.img);
        keyIndicator.setEffect(greyscale);
        keyIndicator.setOpacity(0.25);
        dungeon.attach(value -> {
        	if (value == "has_key=true") {
//        		keyIndicator.setText("Holding key: TRUE");
        		keyIndicator.setEffect(shadow);
        		keyIndicator.setOpacity(1);
        	} else if (value == "has_key=false") {
//        		keyIndicator.setText("Holding key: FALSE");
        		keyIndicator.setEffect(greyscale);
        		keyIndicator.setOpacity(0.25);
        	}
        });        
        
        sidebar.getChildren().add(keyIndicator);
        
        ItemIndicator swordIndicator = new ItemIndicator(Sword.img);
        swordIndicator.setText("1");
        
        sidebar.getChildren().add(swordIndicator);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        case DOWN:
        case LEFT:
        case RIGHT:
            player.move(event.getCode());
            break;
        default:
            break;
        }
    }

}

