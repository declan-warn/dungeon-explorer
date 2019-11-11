package unsw.dungeon.menu;

import unsw.dungeon.util.Observable;
import unsw.dungeon.util.Observer;
import unsw.dungeon.util.SimpleObservable;

public abstract class Controller implements Observable<String> {
	
	private SimpleObservable<String> observable = new SimpleObservable<>();

	@Override
	public void attach(Observer<String> o) {
		this.observable.attach(o);
	}

	@Override
	public void detach(Observer<String> o) {
		this.observable.detach(o);
	}

	@Override
	public void notify(String val) {
		this.observable.notify(val);
	}
	
	public abstract void initialize();
	
}
