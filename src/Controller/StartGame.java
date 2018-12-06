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
	
	public static Group root;
	private long lastNanoTime;
//	private AudioClip sound;
    public static ArrayList<Sprite> enemySprite;
    public static boolean canUpdateBot;
    public static boolean ccheck;
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static LoopGame Loop;
    public static BlackTiger tiger1;
    public static String playerName;
    private static int enemyNumberLimit = 40;
//    public ArrayList<BadHuman> bad = new ArrayList<BadHuman>(); 
    
    //start new game
    private static Timer timerBar;
    private static ScoreBoard scoreBoard;
    private static StatusBar statusBar;
    
	public StartGame(String playerName) {
		super(new Group(), 1250, 800);
		root = (Group) super.getRoot();
		this.playerName = playerName;
		enemySprite = new ArrayList<Sprite>();
		canUpdateBot = true;
		ccheck = true;
		tiger1 = new BlackTiger();
		timerBar = new Timer();
	    scoreBoard = new ScoreBoard();
	    statusBar = new StatusBar(playerName);
	    
		Canvas canvas = new Canvas(1250, 800);
        gc = canvas.getGraphicsContext2D();
        gc.drawImage((Images.stageMap)[0], 0, 0);

//        create tiger onScreenss
        tiger1.setPosition(1250/2 - 351/2, 800/2+100);
        
        Enemy.BadHuman.generatelistBot(enemyNumberLimit);
        BadHuman bad1 = Enemy.BadHuman.generateRandom();
        bad1.setPosition(1250, 800);
        
        //enemySprite.add(bad1);
        enemySprite.addAll(Enemy.BadHuman.getbadList());

        Audio.playBackGround();
        lastNanoTime = System.nanoTime();
        
//        Timer.reset();
//        ScoreBoard.reset();
//        StatusBar.reset(playerName);
        
        root.getChildren().addAll(canvas,statusBar,timerBar,scoreBoard);
        Loop = new LoopGame(this,playerName);
	}

}
