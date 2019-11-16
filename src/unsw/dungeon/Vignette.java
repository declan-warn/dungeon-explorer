package unsw.dungeon;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.entity.Displayable;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;

public class Vignette extends Group {
	
	private final int TILE_WIDTH = 32;
	private final int SEE_RADIUS = 5;

	private int width;
	private int height;
	
	public Vignette(Player ply, int width, int height) {		
		this.width = width;
		this.height = height;
		
		this.track(ply);
		
		ImageView img = new ImageView(new Image("/overlay.png"));
		this.getChildren().add(img);
		
		this.setX(ply.getX());
		this.setY(ply.getY());
	}
	
	private void track(Entity ent) {
		ent.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	setX((int) newValue);
            }
        });
		
        ent.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	setY((int) newValue);
            }
        });
	}
	
	private void setX(int val) {
		this.setTranslateX((val - SEE_RADIUS) * TILE_WIDTH);
	}
	
	private void setY(int val) {
		this.setTranslateY((val - SEE_RADIUS) * TILE_WIDTH);
	}
	
}
