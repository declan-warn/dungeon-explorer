package unsw.dungeon;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import unsw.dungeon.entity.collectable.CollectableEntity;
import unsw.dungeon.entity.collectable.Item;
import unsw.dungeon.entity.collectable.Key;
import unsw.dungeon.entity.collectable.Potion;
import unsw.dungeon.entity.collectable.Sword;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.ItemPickupEvent;
import unsw.dungeon.event.ItemUseEvent;

public class InventoryView extends GridPane implements EventListener {
	
	private final int PADDING = 4;
	private final int WIDTH = 3;
	private final int HEIGHT = 3;
	private final int CELL_SIZE = 32;
	
	private Map<CollectableEntity, ItemIndicator> indicators;
	private ItemIndicator[][] grid;

	public InventoryView() {
		this.setStyle("-fx-background-image: url('/inventory-grid.png'); -fx-background-position: top 2px left 2px;");
        this.setPadding(new Insets(PADDING));
        this.setAlignment(Pos.CENTER);
        this.setHgap(PADDING);
        this.setVgap(PADDING);
        this.setMaxWidth((CELL_SIZE + PADDING) * WIDTH);
        
        for (int x = 0; x < WIDTH; x++) {
        	this.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        
        for (int y = 0; y < HEIGHT; y++) {
        	this.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
        
        this.indicators = new HashMap<>();
        this.grid = new ItemIndicator[HEIGHT][WIDTH];
	}
    
    private void addIndicator(ItemIndicator indicator) {
		this.addToGrid(indicator);
	}

	private void addToGrid(ItemIndicator indicator) {
		int x = 0;
		int y = 0;
		find: {
			for (; y < HEIGHT; y++) {
				for (; x < WIDTH; x++) {
					if (this.grid[y][x] == null) break find;
				}
			}
		}
		
		this.grid[y][x] = indicator;
		this.add(indicator, x, y);
	}
	
	private void removeFromGrid(ItemIndicator indicator) {
		int x = 0;
		int y = 0;
		find: {
			for (; y < HEIGHT; y++) {
				for (; x < WIDTH; x++) {
					if (this.grid[y][x] == indicator) break find;
				}
			}
		}
		
		this.grid[y][x] = null;
		this.getChildren().remove(indicator);
	}
	
	@Override
    public void handle(ItemPickupEvent event) {
		Image img = event.getItem().getImage();
		
		ItemIndicator indicator = new ItemIndicator(img);
		indicator.enable();
		if (event.getRemainingUses() > 1) {
			indicator.setText(String.valueOf(event.getRemainingUses()));
		}
		
		this.addIndicator(indicator);
		
		this.indicators.put(event.getItem(), indicator);
    }

	@Override
    public void handle(ItemUseEvent event) {
    	CollectableEntity item = event.getItem();
    	if (this.indicators.containsKey(item)) {
    		ItemIndicator indicator = this.indicators.get(item);
    		if (item.isType(Item.KEY)) {
    			System.out.println("KEY USED!");
    		}
    		if (event.getRemainingUses() > 0) {
    			indicator.setText(String.valueOf(event.getRemainingUses()));
    		} else {
    			this.indicators.remove(indicator);
    			this.removeFromGrid(indicator);
    			System.out.println("REMOVNg");
    		}
    	}
    }
	
}
