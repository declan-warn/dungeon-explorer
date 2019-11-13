package unsw.dungeon;

import javafx.geometry.Insets;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import unsw.dungeon.goal.Goal;

public class GoalView extends TreeView<String> {

	public GoalView(Goal goal) {
		TreeItem<String> root = new TreeItem<>();
		root.setValue("Dungeon Goal");
		
		this.viewGoal(root, goal);
		
		this.setRoot(root);
		
		this.expandAll(root);
		this.disableCollapseAll(root);
		
		this.setupCustomCells();
	}
	
	public void viewGoal(TreeItem<String> parent, Goal goal) {
		TreeItem<String> child = new TreeItem<>();
		
		if (goal.hasSubgoals()) {
			for (Goal subgoal : goal) {
				this.viewGoal(child, subgoal);
			}
			child.setValue("TODO");
		} else {
			child.setValue("A:" + goal.getClass().toString());
		}
		
		parent.getChildren().add(child);
	}
	
	public void expandAll(TreeItem<?> node) {
		node.setExpanded(true);
		node.getChildren().forEach(this::expandAll);
	}
	
	public void disableCollapseAll(TreeItem<?> node) {
		node.addEventHandler(TreeItem.branchCollapsedEvent(), event -> {
			event.getTreeItem().setExpanded(true);
		});
		node.getChildren().forEach(this::disableCollapseAll);
	}
	
	public void setupCustomCells() {
		this.setCellFactory(tv -> new GoalViewCell<>(tv));
	}
	
}
