package unsw.dungeon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class InventoryView extends GridPane {
	
	private final int PADDING = 4;
	private final int WIDTH = 3;
	private final int HEIGHT = 3;
	private final int CELL_SIZE = 32;

	public InventoryView() {
		this.setStyle("-fx-background-image: url('/inventory-grid.png'); -fx-background-position: top 2px left 2px;");
        this.setPadding(new Insets(PADDING));
        this.setAlignment(Pos.CENTER);
        this.setHgap(PADDING);
        this.setVgap(PADDING);
        
        for (int x = 0; x < WIDTH; x++) {
        	this.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        
        for (int y = 0; y < HEIGHT; y++) {
        	this.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
	}
	
}
