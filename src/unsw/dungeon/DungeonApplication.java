package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import unsw.dungeon.menu.Controller;
import unsw.dungeon.menu.InfoController;
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
    	MenuController controller = new MenuController();
    	controller.attach(val -> {
    		if (val == "play") {
    			try {
					showSelection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		} else if (val == "info") {
    			try {
    				showInfo();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	});
        
    	showScene("Menu", "menu/MenuView.fxml", controller);
    }
    
    public void showInfo() throws IOException {
    	InfoController controller = new InfoController();
    	controller.attach(val -> {
    		if (val == "menu") {
    			try {
    				showMenu();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	});
    	
    	showScene("Info", "menu/InfoView.fxml", controller);
    }
    
    public void showSelection() throws IOException {
    	SelectionController controller = new SelectionController();
    	controller.attach(val -> {
			try {
				if (val == "menu") {
					showMenu();
				} else {
					showDungeon(val);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
        showScene("Selection", "menu/SelectionView.fxml", controller);
    }
    
    public void showDungeon(String dungeonFileName) throws IOException {    	
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeonFileName);
		DungeonController controller = dungeonLoader.loadController();
		
		controller.attach(val -> {
			if (val == "select") {
				try {
					showSelection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		showScene("Dungeon", "DungeonView.fxml", controller);
    }
    
    private void showScene(String title, String fxmlPath, Controller controller) throws IOException {
    	primaryStage.setTitle("Dungeon Explorer - " + title);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
    	loader.setController(controller);
    	
    	Parent root = loader.load();
    	Scene scene = new Scene(root);
    	root.requestFocus();
    	
    	primaryStage.setScene(scene);
    	primaryStage.show();
//    	primaryStage.setMaximized(true);
    	primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
