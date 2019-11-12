package unsw.dungeon;

import java.awt.Paint;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class ItemIndicator extends StackPane {
	
	private ImageView imageView;
	private Text text;
	private Circle circle;
	private StackPane bubble;
	private Group group;
	
	private DropShadow shadow;
	private ColorAdjust greyscale;
	
	private Boolean enabled;

	public ItemIndicator(Image image) {
		super();
		
		this.shadow = new DropShadow();
        shadow.setColor(new Color(0, 0, 0, 0.25));
        shadow.setRadius(2);
        shadow.setSpread(1);
        
        this.greyscale = new ColorAdjust();
        greyscale.setInput(shadow);
        greyscale.setHue(-1);
		
		this.imageView = new ImageView(image);
		
		this.circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setRadius(8);
        circle.relocate(0, 0);
		
		this.text = new Text("");
		text.setBoundsType(TextBoundsType.VISUAL);
		text.setFont(new Font(10));
		
		this.bubble = new StackPane();
		bubble.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
		bubble.getChildren().addAll(circle, text);
		bubble.setAlignment(Pos.CENTER);

		this.group = new Group();
		group.getChildren().addAll(bubble);
		
		this.getChildren().addAll(imageView, group);
		StackPane.setAlignment(group, Pos.BOTTOM_RIGHT);
		
		this.disable();
	}
	
	public void enable() {
		this.enabled = true;
		this.imageView.setEffect(shadow);
		this.setOpacity(1);
	}
	
	public void disable() {
		this.enabled = false;
		this.setText("");
		this.imageView.setEffect(greyscale);
		this.setOpacity(0.25);
	}
	
	public void setText(String text) {
		this.text.setText(text);
		
		this.group.setVisible(!text.isBlank());
	}
	
}
