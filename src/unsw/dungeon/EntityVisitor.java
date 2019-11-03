package unsw.dungeon;

public interface EntityVisitor {

	default public void visit(Entity entity) {};
	
	default public void visit(Exit exit) {};
	
	default public void visit(Treasure treasure) {};
	
	default public void visit(FloorSwitch floorSwitch) {};
	
	default public void visit(Enemy enemy) {};
	
	default public void visit(Potion potion) {};
	
}