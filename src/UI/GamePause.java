package UI;

import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import Controller.Main;
import Controller.ScoreBoard;
import Controller.Timer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;

public class GamePause extends Popup{
	
	public static ArrayList<Image>gamePause = Images.gamePause;
	public static int selectNumber = 100000;
	public static boolean isPause;
	
	public GamePause() {
		super();
		selectNumber = 100000;
		isPause = false;
		Canvas canvas = new Canvas(600, 300);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(gamePause.get(0), 0, 0);
		this.getContent().addAll(canvas);
		this.setAnchorY(800/2-80);
		this.setAnchorX(1250/2-200);
		
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(this.isShowing())
			{
//				System.out.println("class: "+isPause);
				Audio.SELECTMENU.play();
		      if(key.getCode()==KeyCode.DOWN || key.getCode()==KeyCode.RIGHT) {
		    	  selectNumber += 1;
		      }
		      if(key.getCode()==KeyCode.UP || key.getCode()==KeyCode.LEFT) {
		    	  selectNumber -= 1;
		      }
		      if(key.getCode()==KeyCode.SPACE || key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.ALT || key.getCode() == KeyCode.CONTROL || key.getCode() == KeyCode.COMMAND) {
		    	  if(selectNumber%2 == 0){
		    		  //System.out.println(isPause+" Continue!");
		    		  continueGame();
		  			System.out.println("GAME IS PLAYING...");
		    	  }
		    	  else {
		    		  ////Is the game running?
		    		  //System.out.println("GAME OVER!");
		    		  this.hide();
		    		  Timer.stop();
		    		  Timer.hide();
		    		  Timer.terminate();
		    		  ScoreBoard.hide();
		    		  Main.mainMenu();
		    		  System.out.println("BACK TO MAIN MENU!");
		    	  }
		      }
		      else if(key.getCode() == KeyCode.ESCAPE)
		      {
		    	  continueGame();
		      }
		      gc.drawImage(gamePause.get(selectNumber%2), 0, 0);
			}
		});
	}
	
	private void continueGame() {
		selectNumber = 100000;
		this.hide();
		Timer.show();
		Timer.play();
		ScoreBoard.show();
		isPause = false;
	}

}
