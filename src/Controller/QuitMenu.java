package Controller;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;

import application.Images;
import application.Audio;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class QuitMenu extends Popup{
	
	public static ArrayList<Image>quitMenu = Images.quitMenu;
	public static int selectNumber = 100000;
	
	public QuitMenu() {
		super();
		Canvas canvas = new Canvas(600, 300);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(quitMenu.get(0), 0, 0);
		this.getContent().addAll(canvas);
		this.setAnchorY(800/2-80);
		this.setAnchorX(1250/2-200);
		
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(this.isShowing())
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
		    		  Main.gameQuit();
		    	  }
		    	  else {
		    		  selectNumber = 100000;
		    		  this.hide();
		    	  }
		      }
		      else if(key.getCode() == KeyCode.ESCAPE)
		      {
		    	  MainMenu.pressAble = true;
		    	  selectNumber = 100000;
		      }
		      gc.drawImage(quitMenu.get(selectNumber%2), 0, 0);
			}
		});
	}
	

}