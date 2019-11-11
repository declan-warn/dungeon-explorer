package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import unsw.dungeon.menu.MenuController;
import unsw.dungeon.menu.SelectionController;

public class DungeonApplication extends Application {
	
	private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
    	this.primaryStage = primaryStage;
    	
    	this.showMenu();
    }
    
    public void showMenu() throws IOException {
    	primaryStage.setTitle("Dungeon Explorer - Menu");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/MenuView.fxml"));
    	MenuController controller = new MenuController();
    	controller.attach(val -> {
    		if (val == "play") {
    			try {
					showSelection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void showSelection() throws IOException {
    	primaryStage.setTitle("Dungeon Explorer - Selection");
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/SelectionView.fxml"));
    	SelectionController controller = new SelectionController();
//    	controller.attach(val -> {
//    		if (val == "play") {
//    			try {
//					showDungeon();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//    		}
//    	});
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void showDungeon() throws IOException {
    	primaryStage.setTitle("Dungeon Explorer - Dungeon");
    	
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
		
		DungeonController controller = dungeonLoader.loadController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);
		
		Parent root = loader.load();
		Scene scene = new Scene(root);
		root.requestFocus();
		
		primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
