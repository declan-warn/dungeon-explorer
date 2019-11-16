package unsw.dungeon;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;

public class Vignette extends Group {
	
	private final int TILE_SIZE = 32;
	private final int SEE_ACROSS = 11;
	
	// The amount of overlap between the visible area and the rest of the
	// overlay.
	// Lower amounts can cause visual artifacts from floating point errors (I assume).
	// Higher amounts make the circle look less circular.
	private final int FUZZ = 4;

	private int width;
	private int height;
	private int x;
	private int y;
	
	private GraphicsContext gc;
	
	public Vignette(Player ply, int tilesWide, int tilesHigh) {
		this.width = tilesWide * TILE_SIZE;
		this.height = tilesHigh * TILE_SIZE;
		
		Canvas canvas = new Canvas(width, height);
		this.gc = canvas.getGraphicsContext2D();		
		
		this.getChildren().add(canvas);
		
		this.setX(ply.getX());
		this.setY(ply.getY());
		this.track(ply);
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
		this.x = val;
		this.draw();
	}
	
	private void setY(int val) {
		this.y = val;
		this.draw();
	}
	
	private void draw() {		
		gc.clearRect(0, 0, width, height);
		
		gc.setFill(Color.valueOf("#211f1b"));
		gc.fillRect(0, 0, width, height);
		gc.clearRect((x - SEE_ACROSS/2) * TILE_SIZE, (y - SEE_ACROSS/2) * TILE_SIZE, SEE_ACROSS * TILE_SIZE, SEE_ACROSS * TILE_SIZE);
		
		gc.setFill(RadialGradient.valueOf("radial-gradient(center 50% 50%, radius 50%, transparent 75%, #211f1b)"));
		gc.fillRect((x - SEE_ACROSS/2) * TILE_SIZE - FUZZ, (y - SEE_ACROSS/2) * TILE_SIZE - FUZZ, SEE_ACROSS * TILE_SIZE + FUZZ * 2, SEE_ACROSS * TILE_SIZE + FUZZ * 2);
	}
	
}
