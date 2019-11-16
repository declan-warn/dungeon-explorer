package unsw.dungeon.menu;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import unsw.dungeon.FontManager;

public class SelectionButton extends Button {
	
	public SelectionButton(String text) {
		super();
		this.setMinSize(398, 40);
		
		Label lb = new Label(text);
		lb.setFont(FontManager.getTitleFont(18));
		
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(0);
		shadow.setOffsetY(0);
		shadow.setColor(Color.valueOf("#221815"));
		shadow.setRadius(2);
		
		lb.setEffect(shadow);
		
		this.setGraphic(lb);
	}

}
