package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.text.Font;

public class FontRes {

    public static Font getFont() {
    	Font font = null;
		try {
			font = Font.loadFont(new FileInputStream(new File("resources/Cornerstone.ttf")), 12);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return font;
	}
    
    public static void load() {
    	try {
			Font.loadFont(new FileInputStream(new File("resources/Cornerstone.ttf")), 12);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	//public static final Font mcfont = Font.createFont(Font.TRUETYPE_FONT,new Font(getClass().getClassLoader().getResourceAsStream("assets/minecraft/font/mcfont.ttf")));
}
