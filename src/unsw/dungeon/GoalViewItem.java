package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import unsw.dungeon.goal.Goal;

public class GoalViewItem extends VBox {
	
	public static final int INDENT = 16;
	
	private Goal goal;
	private HBox box;
	private CheckBox check;
	private Text textNode;
	
	private List<GoalViewItem> subitems;
	
	public GoalViewItem(Goal goal, int level) {
		this.goal = goal;
		
		this.box = new HBox();
		this.box.setAlignment(Pos.CENTER_LEFT);
		this.box.setSpacing(4);
		
		this.check = new CheckBox();
		this.check.setIndeterminate(false);
		this.check.setDisable(true);
		
		this.textNode = new Text();
		this.textNode.setFont(FontManager.getBodyFont(16));
		
		this.box.getChildren().addAll(check, textNode);
		this.getChildren().add(box);
		
		this.subitems = new ArrayList<>();
		
		this.setPadding(new Insets(0, 0, 0, level * INDENT));
		this.setText(goal.toString());
		for (Goal subgoal : goal) {
			GoalViewItem item = new GoalViewItem(subgoal, level + 1);
			this.addChild(item);
		}
	}
	
	public GoalViewItem(Goal goal) {
		this(goal, 0);
	}
	
	public void addChild(GoalViewItem node) {
		this.getChildren().add(node);
		this.subitems.add(node);
	}
	
	public void setText(String text) {
		this.textNode.setText(text);
	}

	public void handleCompletion(Goal completedGoal) {
		if (this.goal == completedGoal) {
			this.check.setSelected(true);
		} else {
			this.subitems.forEach(item -> item.handleCompletion(completedGoal));
		}
	}
	
}
