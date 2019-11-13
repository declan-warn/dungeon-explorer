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
import unsw.dungeon.entity.collectable.Potion;
import unsw.dungeon.entity.collectable.Sword;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.ItemPickupEvent;
import unsw.dungeon.event.ItemUseEvent;
import unsw.dungeon.menu.Controller;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends Controller implements EventListener {

    @FXML
    private GridPane squares;
    
    @FXML
    private VBox sidebar;
    
    @FXML
    private GridPane inventory;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        
        dungeon.registerListener(this);
    }
    
    private ItemIndicator swordIndicator;
    private ItemIndicator keyIndicator;
    private ItemIndicator potionIndicator;

    @FXML
    public void initialize() {
        Image ground = new Image("/floor.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        sidebar.setPadding(new Insets(16));
        sidebar.setAlignment(Pos.TOP_CENTER);
        
        sidebar.setStyle("-fx-background-image: url('/stonebrick.png'); -fx-background-position: top right;");
        
        this.keyIndicator = new ItemIndicator(Key.img);
        this.swordIndicator = new ItemIndicator(Sword.img);
        this.potionIndicator = new ItemIndicator(Potion.img);
        
        sidebar.getChildren().addAll(keyIndicator, swordIndicator, potionIndicator);
        
        
        sidebar.getChildren().add(new InventoryView());
    }
    
    @Override
    public void handle(ItemPickupEvent event) {
    	switch (event.getType()) {
    	case SWORD:
    		this.swordIndicator.enable();
    		this.swordIndicator.setText("5");
    		break;
    		
    	case KEY:
    		this.keyIndicator.enable();
    		break;
    		
    	case POTION:
    		this.potionIndicator.enable();
    		this.potionIndicator.setText("10");
    		break;

		default:
			break;
    	}
    }
    
    @Override
    public void handle(ItemUseEvent event) {
    	switch (event.getType()) {
    	case SWORD:
    		if (event.getRemainingUses() > 0) {
    			this.swordIndicator.setText(String.valueOf(event.getRemainingUses()));
    		} else {
    			this.swordIndicator.disable();
    		}
    		break;
    		
    	case KEY:
    		this.keyIndicator.disable();
    		break;
    		
    	case POTION:
    		if (event.getRemainingUses() > 0) {
    			this.potionIndicator.setText(String.valueOf(event.getRemainingUses()));
    		} else {
    			this.potionIndicator.disable();    			
    		}
    		break;
    		
    	default:
    		break;
    	}
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

