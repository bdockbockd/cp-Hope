package Controller;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HowToPlayMenu extends Scene{
	public static Pane root = new Pane();
	public static Image howToPlayImage = new Image(ClassLoader.getSystemResource("design/howtoplay/howtoplay.png").toString());
	public HowToPlayMenu() {
		super(root,1250,800);
		Canvas canvas = new Canvas(1250, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(howToPlayImage, 0, 0);
		root.getChildren().addAll(canvas);
		Main.backMenu(this);
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
		      if(key.getCode()==KeyCode.SPACE || key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.ALT || key.getCode() == KeyCode.CONTROL || key.getCode() == KeyCode.COMMAND)
		      {
		    	  Main.keySound();
		    	  Main.mainMenu();
		      }
		});
	}
	

}