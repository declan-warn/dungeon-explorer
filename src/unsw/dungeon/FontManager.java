package unsw.dungeon;

import java.net.URL;

import javafx.scene.text.Font;

public class FontManager {

//	static public Map<FontType, Font> loadedFonts = new HashMap<>();
	
	static public Font getTitleFont(int size) {
		return FontManager.loadFont("Alagard.ttf", size);
	}
	
	static public Font getBodyFont(int size) {
		return FontManager.loadFont("Romulus.ttf", size);
	}
	
	static public Font loadFont(String path, int size) {
		URL url = ClassLoader.getSystemResource(path);
		if (url == null) return null;
		Font font = Font.loadFont(url.toExternalForm(), size);
		return font;
	}
	
}
