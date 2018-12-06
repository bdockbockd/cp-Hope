package Controller;

import application.Images;
import application.Audio;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class HowToPlayMenu extends Scene{
	
	public static Pane root;
	public static Image howToPlayImage = Images.howToPlayImage;
	
	public HowToPlayMenu() {
		super(new Pane(),1250,800);
		root = (Pane) super.getRoot();
		Canvas canvas = new Canvas(1250, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(howToPlayImage, 0, 0);
		root.getChildren().addAll(canvas);
		Main.backMenu(this);
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
		      if(key.getCode()==KeyCode.SPACE || key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.ALT || key.getCode() == KeyCode.CONTROL || key.getCode() == KeyCode.COMMAND)
		      {
		    	  Audio.SELECTMENU.play();
		    	  Main.mainMenu();
		      }
		});
	}
	

}