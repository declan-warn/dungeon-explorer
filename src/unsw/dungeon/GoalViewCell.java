package unsw.dungeon;

import javafx.beans.InvalidationListener;
import javafx.css.PseudoClass;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

// Adapted from:
// <https://stackoverflow.com/questions/54953447/javafx-how-do-i-hide-the-drop-down-arrow-in-a-treeview>

public class GoalViewCell<T> extends TreeCell<T> {

	private TreeView<T> tree;
	
    public static <T> Callback<TreeView<T>, TreeCell<T>> forTreeView() {
        return treeView -> new GoalViewCell<T>(treeView);
    }

    public GoalViewCell(TreeView<T> tree) {
    	this.tree = tree;
    	
        getStyleClass().add("goal-view__cell");        
        this.getStylesheets().add("unsw/dungeon/GoalView.css");
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            graphicProperty().unbind();
            setGraphic(null);
        } else {
            setText(item.toString()); // Really only works if item is a String. Change as needed.
            graphicProperty().bind(getTreeItem().graphicProperty());
            
            int level = tree.getTreeItemLevel(this.getTreeItem());
            System.out.println(item.toString() + " : " + level + " : ");
            this.setStyle("-fx-border-insets: 0 0 0 " + (level * 8) + ";");
        }
    }

}