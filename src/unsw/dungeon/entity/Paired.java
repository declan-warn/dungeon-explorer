package unsw.dungeon.entity;

public interface Paired {
	
	public int getId();
	
	default public boolean hasId(int id) {
		return this.getId() == id;
	}
}
