package unsw.dungeon;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import unsw.dungeon.goal.Goal;

public class GoalViewItem extends VBox {
	
	public static final int INDENT = 16;
	
	private Text textNode;
	
	public GoalViewItem() {
		this.textNode = new Text();
		this.getChildren().add(textNode);
	}
	
	public GoalViewItem(Goal goal, int level) {
		this();
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
	
	public void addChild(Node node) {
		this.getChildren().add(node);
	}
	
	public void setText(String text) {
		this.textNode.setText(text);
	}
	
}
