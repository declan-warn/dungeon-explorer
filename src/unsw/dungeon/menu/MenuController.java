package unsw.dungeon.menu;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unsw.dungeon.FontManager;
import unsw.dungeon.util.Observable;
import unsw.dungeon.util.Observer;
import unsw.dungeon.util.SimpleObservable;

public class MenuController extends Controller {
	
	private SimpleObservable<String> observable = new SimpleObservable<>();

	@FXML
    private VBox vbox;

    @FXML
    public void initialize() {
    	vbox.getStylesheets().add("unsw/dungeon/menu/selection.css");

    	vbox.setPadding(new Insets(64));
    	vbox.setSpacing(16);

    	Label title = new Label("Dungeon Explorer");
    	title.setFont(FontManager.getTitleFont(32));
    	title.setPadding(new Insets(16, 32, 16, 32));
    	title.setStyle("-fx-background-image: url('/banner.png'); -fx-background-size: 100% 100%; -fx-text-fill: #634332");
    	
    	Region space = new Region();
    	space.setMinHeight(32);
    	
    	Button play = new SelectionButton("Play");
    	play.setOnMouseClicked(event -> {
    		this.notify("play");
    	});

    	Button info = new SelectionButton("Instructions");
    	info.setOnMouseClicked(event -> {
    		this.notify("info");
    	});
    	
    	
    	this.vbox.getChildren().addAll(title, space, play, info);
    }
   
}