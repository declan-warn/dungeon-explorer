package unsw.dungeon;

public interface EntityVisitor {

	default public void visit(Entity entity) {};
	
	default public void visit(Exit exit) {};
	
	default public void visit(Treasure treasure) {};
	
}
