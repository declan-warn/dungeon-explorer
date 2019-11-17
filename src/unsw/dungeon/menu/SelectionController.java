package unsw.dungeon.menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import unsw.dungeon.FontManager;

public class SelectionController extends Controller {
	
	@FXML
    private TilePane grid;
	
	@FXML
	public void initialize() {
		grid.setPadding(new Insets(64));
		grid.setPrefColumns(1);
		
		Button exit = new SelectionButton("Back");
		exit.setOnMouseClicked(event -> {
			this.notify("menu");
		});
		grid.getChildren().add(exit);
		
		Label title = new Label("Maps");
    	title.setFont(FontManager.getTitleFont(24));
    	title.setPadding(new Insets(16, 32, 16, 32));
    	title.setStyle("-fx-background-image: url('/banner.png'); -fx-background-size: 100% 100%; -fx-text-fill: #634332");
    	grid.getChildren().add(title);
			
		try (Stream<Path> paths = Files.walk(Paths.get("./dungeons"))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(this::addDungeon);
		} catch (IOException e) {
			
		}
		
		grid.getStylesheets().add("unsw/dungeon/menu/selection.css");
	}
	
	public void addDungeon(Path dungeonPath) {
		String dungeonFileName = dungeonPath.getFileName().toString();		
		
		Button btn = new SelectionButton(getDisplayName(dungeonFileName));
		btn.setOnMouseClicked(event -> notify(dungeonFileName));
		grid.getChildren().add(btn);
	}
	
	public String getDisplayName(String filename) {
		String noExt = filename.replaceFirst("\\.json$", "");
		char[] display = new char[noExt.length()];
		boolean shouldCapitalize = true;
		int i = 0;
		for (char c : noExt.toCharArray()) {
			if (shouldCapitalize) {
				c = Character.toUpperCase(c);
				shouldCapitalize = false;
			}
			if (c == '_') {
				c = ' ';
			}
			display[i++] = c;
			if (c == ' ') {
				shouldCapitalize = true;
			}
		}
		return String.valueOf(display);
	}
	
	@FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case ESCAPE:
        	this.notify("menu");
        	break;
        default:
            break;
        }
    }
	
}