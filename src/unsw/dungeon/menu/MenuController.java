package unsw.dungeon.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import unsw.dungeon.util.Observable;
import unsw.dungeon.util.Observer;
import unsw.dungeon.util.SimpleObservable;

public class MenuController implements Observable<String> {
	
	private SimpleObservable<String> observable = new SimpleObservable<>();

	@FXML
    private ImageView logo;
	
    @FXML
    private Button btnPlay;

    @FXML
    public void initialize() {
    	Image logoImage = new Image("/logo.png");
    	this.logo.setImage(logoImage);
    }
    
    @FXML
    void gamePlay(MouseEvent event) {
    	this.notify("play");
    }

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

}