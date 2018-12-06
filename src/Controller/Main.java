package Controller;
	
import java.util.ArrayList;

import Enemy.*;
import Sprite.*;
import application.Images;
import application.LoopGame;
import application.Audio;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import Controller.*;
public class Main extends Application {

	public static Stage stage;
	//public static ArrayList<Image> selectMenu = new ArrayList<Image>();
	public static int selectNumber = 1000000;
	public static MainMenu mainMenu;
	public static GameMenu gameMenu;
	public static StartGame startGame;
	public static HowToPlayMenu howToPlayMenu;
	public static HallOfFameMenu hallOfFameMenu;
	public static QuitMenu quitMenu;
	//public static GamePause gamePause;
	//public static DeadScene deadScene;

	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setTitle("blackPantherX"); 
			mainMenu = new MainMenu();
			gameMenu = new GameMenu();
			howToPlayMenu = new HowToPlayMenu();
			hallOfFameMenu = new HallOfFameMenu();
			quitMenu = new QuitMenu();
			
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            @Override
	            public void handle(WindowEvent t) {
	            	HallOfFameMenu.save();
	                Platform.exit();
	                System.exit(0);
	            }
	        });
			stage.setScene(mainMenu);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void mainMenu() {
		stage.setScene(mainMenu);
	}
	public static void gameMenu() {
		stage.setScene(gameMenu);
	}
	public static void howToPlayMenu() {
		stage.setScene(howToPlayMenu);
	}
	public static void hallOfFameMenu() {
		HallOfFameMenu.fillText();
		stage.setScene(hallOfFameMenu);
	}
	public static void quitMenu() {
		quitMenu = new QuitMenu();
		MainMenu.pressAble = false;
		quitMenu.show(stage);
	}
	public static void gamePause() {
		//gamePause.show(stage);
	}
	public static void deadScene() { 
		//deadScene.show(stage);
	}
	public static void startGame(String playerName) {
		startGame = new StartGame(playerName);
		stage.setScene(startGame);
	}
	public static void gameQuit(){
	    // get a handle to the stage
	    //Stage stage = (Stage) closeButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	
	public static void backMenu(Scene s) {
		s.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
		      if(key.getCode() == KeyCode.ESCAPE)
		      {
		    	  Audio.SELECTMENU.play();
		    	  Main.mainMenu();
		      }
		});
	}
	
}
