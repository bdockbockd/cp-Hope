package Controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainMenu extends Scene{
	public static ArrayList<Image> selectMenu = new ArrayList<Image>();
	public static int selectNumber = 1000000;
	public static Pane root = new Pane();
	public static boolean pressAble = true;
	public MainMenu() {
		super(root,1250,800);
		Canvas canvas = new Canvas(1250, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		
		for(int i = 0;i < 4;i++)
		{
			selectMenu.add(new Image(ClassLoader.getSystemResource("design/mainmenu/mainmenu-0"+(i+1)+".png").toString()));
		}
		gc.drawImage(selectMenu.get(0), 0, 0);
		
		root.getChildren().add(canvas);
		
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(!Main.quitMenu.isShowing() && pressAble == false)
			{
				pressAble = true;
			}
			else if(!Main.quitMenu.isShowing()) {
		      if(key.getCode()==KeyCode.DOWN || key.getCode()==KeyCode.RIGHT) {
		    	  Main.keySound();
		    	  selectNumber += 1;
		      }
		      if(key.getCode()==KeyCode.UP || key.getCode()==KeyCode.LEFT) {
		    	  Main.keySound();
		    	  selectNumber -= 1;
		      }
		      if(key.getCode()==KeyCode.ESCAPE)
		      {
		    	  Main.keySound();
		    	  Main.quitMenu();
		      }
		      if(key.getCode()==KeyCode.SPACE || key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.ALT || key.getCode() == KeyCode.CONTROL || key.getCode() == KeyCode.COMMAND) {
		    	  //System.out.println(selectNumber%4);
		    	  Main.keySound();
		    	  switch(selectNumber%4) {
		    	  case 0: Main.gameMenu(); break;
		    	  case 1: Main.howToPlayMenu();break;
		    	  case 2: Main.hallOfFameMenu();break;
		    	  case 3: Main.quitMenu();break;
		    	  }
		      }
		      gc.drawImage(selectMenu.get(selectNumber%4), 0, 0);
			}
		});
	}
	
}
