package UI;

import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Controller.LoopGame;
import Controller.ScoreBoard;
import Controller.StatusBar;
import Controller.Timer;
import Enemy.BadHuman;
import Item.Meat;
import Sprite.BlackPanther;
import Sprite.Sprite;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class StartGame extends Scene {
	
	public static Group root;

//	private AudioClip sound;
//    public static ArrayList<Sprite> enemySprite;
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static LoopGame Loop;
    public static String playerName;
    private static int ENEMYSTARTNUMBER = 30;
//    public ArrayList<BadHuman> bad = new ArrayList<BadHuman>(); 
    
    //start new game
    private static Timer timerBar;
    private static ScoreBoard scoreBoard;
    private static StatusBar statusBar;
    
	public StartGame(String playerName) {
		super(new Group(), 1250, 800);
		root = (Group) super.getRoot();
		StartGame.playerName = playerName;
//		enemySprite = new ArrayList<Sprite>();
		timerBar = new Timer();
	    scoreBoard = new ScoreBoard();
	    statusBar = new StatusBar(playerName);
	    
		Canvas canvas = new Canvas(1250, 800);
        gc = canvas.getGraphicsContext2D();
        gc.drawImage((Images.stageMap)[0], 0, 0);
        

//        create tiger onScreenss
        
        EnemyGen.generatelistBot(ENEMYSTARTNUMBER);
        BadHuman bad1 = EnemyGen.generateRandom();
        bad1.setPosition(1250, 800);
        
        //enemySprite.add(bad1);
//        enemySprite.addAll(Enemy.EnemyGen.getbadList());

        Audio.GAME_BGM.play();
        
//        Timer.reset();
//        ScoreBoard.reset();
//        StatusBar.reset(playerName);
        
        root.getChildren().addAll(canvas,statusBar,timerBar,scoreBoard);
        Loop = new LoopGame(gc,this,playerName);
	}

}
