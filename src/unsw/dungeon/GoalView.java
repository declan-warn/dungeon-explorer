package unsw.dungeon;

import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import unsw.dungeon.event.EventListener;
import unsw.dungeon.event.GoalCompletionEvent;
import unsw.dungeon.goal.Goal;

public class GoalView extends VBox implements EventListener {
	
	private GoalViewItem goalList;
	
	public GoalView(Goal goal) {
		Text title = new Text("GOALS");
		title.setFont(FontManager.getTitleFont(16));
		
		Separator sep = new Separator();
		
		this.goalList = new GoalViewItem(goal);
		
		this.getChildren().addAll(title, sep, goalList);
		
		this.setId("goals");
		this.getStylesheets().add("unsw/dungeon/GoalView.css");
	}
	
	@Override
	public void handle(GoalCompletionEvent event) {
		this.goalList.handleCompletion(event.getGoal());
	}
	
}