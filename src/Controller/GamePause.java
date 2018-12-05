package Controller;

import java.util.ArrayList;

import application.Audio;
import application.Images;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;

public class GamePause extends Popup{
	
	public static ArrayList<Image>gamePause = Images.gamePause;
	public static int selectNumber = 100000;
	
	public GamePause() {
		super();
		Canvas canvas = new Canvas(600, 300);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(gamePause.get(0), 0, 0);
		this.getContent().addAll(canvas);
		this.setAnchorY(800/2-80);
		this.setAnchorX(1250/2-200);
		
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(Main.gamePause.isShowing())
			{
				Audio.SELECTMENU.play();
		      if(key.getCode()==KeyCode.DOWN || key.getCode()==KeyCode.RIGHT) {
		    	  selectNumber += 1;
		      }
		      if(key.getCode()==KeyCode.UP || key.getCode()==KeyCode.LEFT) {
		    	  selectNumber -= 1;
		      }
		      if(key.getCode()==KeyCode.SPACE || key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.ALT || key.getCode() == KeyCode.CONTROL || key.getCode() == KeyCode.COMMAND) {
		    	  if(selectNumber%2 == 0){
		    		  selectNumber = 100000;
		    		  this.hide();
		    	  }
		    	  else {
		    		  this.hide();
		    		  Main.mainMenu();
		    	  }
		      }
		      else if(key.getCode() == KeyCode.ESCAPE)
		      {
		    	  //MainMenu.pressAble = true;
		    	  selectNumber = 100000;
		      }
		      gc.drawImage(gamePause.get(selectNumber%2), 0, 0);
			}
		});
	}

}
