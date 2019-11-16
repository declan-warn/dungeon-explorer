package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import unsw.dungeon.event.EventListener;

public class ScoreView extends VBox implements EventListener {
	
	public ScoreView(IntegerProperty score) {
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add("/unsw/dungeon/ScoreView.css");
		
		Label lb = new Label("Score");
		lb.setFont(FontManager.getTitleFont(18));
		
		Label sc = new Label(String.valueOf(score.get()));
		sc.setFont(FontManager.getBodyFont(32));
		
		score.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				sc.setText(String.valueOf(newValue));
			}
			
		});
		
		this.getChildren().addAll(lb, sc);
	}

}
