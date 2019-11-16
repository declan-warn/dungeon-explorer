package unsw.dungeon.menu;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import unsw.dungeon.FontManager;

public class InfoController extends Controller {

	@FXML
	private ScrollPane scroll;
	
	@FXML
    private VBox vbox;
	
	@Override
	public void initialize() {
		scroll.setMaxHeight(400);
		scroll.setFitToWidth(true);
		
		vbox.setMinSize(600, 400);
		vbox.setPadding(new Insets(64));
		vbox.getStylesheets().add("/unsw/dungeon/menu/selection.css");
		vbox.setAlignment(Pos.CENTER);
		
		Button back = new SelectionButton("Back");
		back.setOnMouseClicked(event -> {
			this.notify("menu");
		});
		add(back);
		
		addTitle("How To Play", 32);
		addTitle("Movement", 24);
		addBody("/arrows.png", "Use the arrows keys to move.");
		
		addTitle("Goals", 24);
		addBody("/goals_demo.png", "Complete the goals shown on the left hand side of the screen to complete the dungeon.");
		
		addTitle("Keys & Doors", 24);
		addBody("/key_door_demo.png", "Collect keys to unlock doors and progress through the dungeon.");
		
		addTitle("Portals", 24);
		addBody("/portal_demo.png", "Portals allow you to quickly move across the dungeon by teleporting between two paired portals.");
		
		addTitle("Enemies", 24);
		addBody("/deep_elf_master_archer.png", "Beware of enemies! You will die upon touching an enemy.");
		
		addTitle("Swords + Potions", 24);
		addBody("/sword_potion_demo.png", "Use these to defeat enemies.");
		
		addTitle("Visibility", 24);
		addBody("/visibility_demo.png", "You can only see your immediate surroundings so beware when navigating the dungeon.");
	}
	
	private void add(Node node) { 
		this.vbox.getChildren().add(node);
	}
	
	private void addTitle(String text, int size) {
		Region space = new Region();
		space.setMinHeight(36);
		add(space);
		
		Label title = new Label(text);
		title.setFont(FontManager.getTitleFont(size));
		add(title);
	}
	
	private void addBody(String imgPath, String text) {
		Image img = new Image(imgPath);
		ImageView imgView = new ImageView(img);
		imgView.maxHeight(128);
		
		Label body = new Label(text);
		body.setFont(FontManager.getBodyFont(16));
		
		VBox item = new VBox(imgView, body);
		item.setPadding(new Insets(24, 0, 0, 0));
		item.setAlignment(Pos.CENTER);
		item.setSpacing(24);
		add(item);
	}

}