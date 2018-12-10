package Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.text.Font;

public class FontRes {

	public static void load() {
		Font.loadFont(ClassLoader.getSystemResource("Cornerstone.ttf").toExternalForm(), 12);
	}

}
