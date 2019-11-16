package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.collectable.Item;
import unsw.dungeon.entity.collectable.Key;
import unsw.dungeon.entity.collectable.Potion;
import unsw.dungeon.entity.collectable.Sword;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.GoalCompletionEvent;
import unsw.dungeon.event.ItemPickupEvent;
import unsw.dungeon.event.ItemUseEvent;
import unsw.dungeon.event.MovementEvent;
import unsw.dungeon.menu.Controller;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends Controller implements EventListener {

	@FXML
    private StackPane centerStack;
	
    @FXML
    private GridPane squares;
    
    @FXML
    private VBox sidebar;
    
    @FXML
    private GridPane inventory;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private Group overlay;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        
        dungeon.registerListener(this);
    }
    
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
        sidebar.setSpacing(24);
        
        sidebar.setStyle("-fx-background-image: url('/stonebrick.png'); -fx-background-position: top right;");
        
        InventoryView inv = new InventoryView();
        sidebar.getChildren().add(inv);
        dungeon.registerListener(inv);
        
        ScoreView score = new ScoreView(dungeon.getScoreProperty());
        sidebar.getChildren().add(score);
        dungeon.registerListener(score);
        
        GoalView goals = new GoalView(dungeon.getGoal());
        sidebar.getChildren().add(goals);
        dungeon.registerListener(goals);
        
//        this.overlay = new Group(new ImageView(new Image("/overlay.png")));
//        centerStack.getChildren().add(overlay);
//        StackPane.setAlignment(overlay, Pos.TOP_LEFT);
//        overlay.setTranslateX((player.getX() - 5) * 32);
//        overlay.setTranslateY((player.getY() - 5) * 32);
//        
        Vignette vignette = new Vignette(player, dungeon.getWidth(), dungeon.getHeight());
        centerStack.getChildren().add(vignette);
        StackPane.setAlignment(vignette, Pos.TOP_LEFT);
        
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
        case ESCAPE:
        	this.showSelection();
        	break;
        default:
            break;
        }
    }
    
    @Override
    public void handle(GoalCompletionEvent event) {
    	if (dungeon.getGoal() == event.getGoal()) {
    		this.showSelection();
    	}
    }
    
//    @Override
//    public void handle(MovementEvent event) {
//    	if (event.isPlayer()) {
//    		event.andThen(e -> {
//    			overlay.setTranslateX((e.getX() - 5) * 32);
//    			overlay.setTranslateY((e.getY() - 5) * 32);
//    		});
//    	}
//    }
    
    public void showSelection() {
    	this.notify("select");
    }

}

