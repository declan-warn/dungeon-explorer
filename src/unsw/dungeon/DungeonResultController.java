package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import unsw.dungeon.entity.ExitStatus;
import unsw.dungeon.menu.Controller;
import unsw.dungeon.menu.SelectionButton;

public class DungeonResultController extends Controller {
	
	@FXML
    private VBox vbox;
	
	private ExitStatus status;
	private int score;

	public DungeonResultController(ExitStatus status, int score) {
		this.status = status;
		this.score = score;
	}

	@Override
	public void initialize() {
		vbox.setMinSize(600, 400);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(24);
		
		vbox.getStylesheets().add("/unsw/dungeon/ScoreView.css");
		vbox.getStylesheets().add("/unsw/dungeon/menu/selection.css");
		
		Label result = new Label();
		result.setFont(FontManager.getTitleFont(48));
		if (this.status == ExitStatus.FAILURE) {
			result.setText("You have died");
		} else {
			result.setText("Congratulations");
		}
		
		Label score = new Label("Score: " + String.valueOf(this.score));
		score.setFont(FontManager.getBodyFont(42));
		
		Button retry = new SelectionButton("Retry");
		retry.setOnMouseClicked(event -> {
			this.notify("retry");
		});
		
		Button exit = new SelectionButton("Exit");
		exit.setOnMouseClicked(event -> {
			this.notify("select");
		});
		
		vbox.getChildren().addAll(result, score, retry, exit);
	}

}