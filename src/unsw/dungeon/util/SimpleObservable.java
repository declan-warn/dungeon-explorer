package unsw.dungeon.util;
import java.util.HashSet;
import java.util.Set;

public class SimpleObservable<T> implements Observable<T> {
	
	private Set<Observer<T>> observers = new HashSet<>();

	@Override
	public void attach(Observer<T> o) {
		this.observers.add(o);
	}

	@Override
	public void detach(Observer<T> o) {
		this.observers.remove(o);
	}

	@Override
	public void notify(T val) {
		this.observers.forEach(observer -> observer.update(val));
	}

}
