package Controller;
	
import java.util.ArrayList;

import Enemy.*;
import Sprite.*;
import application.Images;
import application.LoopGame;
import application.Music;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import Controller.*;
public class Main extends Application {
	private long lastNanoTime;
//	private AudioClip sound;
    public static ArrayList<Sprite> enemySprite = new ArrayList<Sprite>();
    public static boolean canUpdateBot = true;
    public static boolean ccheck = true;
    public static GraphicsContext gc;
    public static LoopGame Loop;
    public static BlackTiger tiger1 = new BlackTiger();
    
    public ArrayList<BadHuman> bad = new ArrayList<BadHuman>();


	@Override 
	public void start(Stage primaryStage) { 
		
		primaryStage.setTitle("blackPantherX");
		Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        Canvas canvas = new Canvas(1250,800);
        Main.gc = canvas.getGraphicsContext2D();
        gc.drawImage((Images.stageMap)[0], 0, 0);
        
        //StatusBar
        String playerName = "Player";
        StatusBar statusBar = new StatusBar(playerName);
         
        //create tiger onScreenss
        tiger1.setPosition(300, 300);
        
        Enemy.BadHuman.generatelistBot(5);
        BadHuman bad1 = Enemy.BadHuman.generateRandom();
        bad1.setPosition(300, 300);
//        enemySprite.add(bad1);
        enemySprite.addAll(Enemy.BadHuman.getbadList());

        Music.playBackGround();
        lastNanoTime = System.nanoTime();
        
        Timer timerBar = new Timer();
        Scoreboard scoreBoard = new Scoreboard();

        root.getChildren().addAll( canvas,statusBar,timerBar,scoreBoard);
        Loop = new LoopGame(theScene);
		primaryStage.show();
//        primaryStage.setFullScreen(true);
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
}
