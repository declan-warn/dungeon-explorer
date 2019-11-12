package unsw.dungeon.menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

public class SelectionController extends Controller {
	
	@FXML
    private TilePane grid;
	
	@FXML
	public void initialize() {		
		try (Stream<Path> paths = Files.walk(Paths.get("./dungeons"))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(this::addDungeon);
		} catch (IOException e) {
			
		}
	}
	
	public void addDungeon(Path dungeonPath) {
		System.out.println("WTF" + dungeonPath);
		String dungeonFileName = dungeonPath.getFileName().toString();
		Button button = new Button(dungeonFileName);
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		button.setOnMouseClicked(event -> {
			notify(dungeonFileName);
		});		
		grid.getChildren().add(button);
	}
	
}