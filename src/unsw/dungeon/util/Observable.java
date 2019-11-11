package unsw.dungeon.util;

public interface Observable<T> {
	
	public void attach(Observer<T> o);
	
	public void detach(Observer<T> o);
	
	public void notify(T val);
	
}
