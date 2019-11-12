package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;
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
        
        Label keyIndicator = new Label("Holding key: " + dungeon.hasItem(Item.KEY));
        dungeon.attach(value -> {
        	if (value == "has_key=true") {
        		keyIndicator.setText("Holding key: TRUE");
        	} else if (value == "has_key=false") {
        		keyIndicator.setText("Holding key: FALSE");
        	}
        });
        
        sidebar.getChildren().add(keyIndicator);
        
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

