package Controller;

import java.util.ArrayList;

import Enemy.BadHuman;
import Sprite.BlackTiger;
import Sprite.Meat;
import Sprite.Sprite;
import application.Images;
import application.LoopGame;
import application.Audio;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class StartGame extends Scene {
	
	public static Group root = new Group();
	private long lastNanoTime;
//	private AudioClip sound;
    public static ArrayList<Sprite> enemySprite = new ArrayList<Sprite>();
    public static boolean canUpdateBot = true;
    public static boolean ccheck = true;
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static LoopGame Loop;
    public static BlackTiger tiger1 = new BlackTiger();
    public static String playerName;
    private static int enemyNumberLimit = 1000;
//    public ArrayList<BadHuman> bad = new ArrayList<BadHuman>(); 
    
	public StartGame(String playerName) {
		super(root, 1250, 800);
		this.playerName = playerName;
        canvas = new Canvas(1250,800);
        gc = canvas.getGraphicsContext2D();
        gc.drawImage((Images.stageMap)[0], 0, 0);
        
//        StatusBar
        StatusBar statusBar = new StatusBar(this.playerName);
         
//        create tiger onScreenss
        tiger1.setPosition(1250/2 - 351/2, 800/2+100);
        
        Enemy.BadHuman.generatelistBot(enemyNumberLimit);
        BadHuman bad1 = Enemy.BadHuman.generateRandom();
        bad1.setPosition(1250, 800);
        
        //enemySprite.add(bad1);
        enemySprite.addAll(Enemy.BadHuman.getbadList());

        Audio.playBackGround();
        lastNanoTime = System.nanoTime();

        Timer timerBar = new Timer();
        ScoreBoard scoreBoard = new ScoreBoard();
        
        root.getChildren().addAll( canvas,statusBar,timerBar,scoreBoard);
        Loop = new LoopGame(this);
	}

}
