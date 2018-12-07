package Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.text.Font;

public class FontRes {
    
    public static void load() {
    	Font.loadFont(ClassLoader.getSystemResource("Cornerstone.ttf").toExternalForm(), 12);
//    	try {
//			Font.loadFont(new FileInputStream(new File("resources/Cornerstone.ttf")), 12);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
	
	//public static final Font mcfont = Font.createFont(Font.TRUETYPE_FONT,new Font(getClass().getClassLoader().getResourceAsStream("assets/minecraft/font/mcfont.ttf")));
}
