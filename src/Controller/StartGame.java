package Controller;

import java.util.ArrayList;

import Enemy.BadHuman;
import Sprite.BlackTiger;
import Sprite.Sprite;
import application.Images;
import application.Music;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class StartGame extends Scene {
	public static Group root = new Group();
	private long lastNanoTime;
//    private AudioClip sound;
    public static ArrayList<String> input = new ArrayList<String>();
    public static final ArrayList<String> type2Key = new ArrayList<String>();
    public static ArrayList<String> input2 = new ArrayList<String>();
    public static ArrayList<Sprite> enemySprite = new ArrayList<Sprite>();
    public static boolean canUpdateBot = true;
    public static boolean ccheck = true;
    public static String playerName;
    
	public StartGame(String playerName) {
		super(root, 1250, 800);
		this.playerName = playerName;
        Canvas canvas = new Canvas(1250,800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage((Images.stageMap)[0], 0, 0);
        
//        StatusBar
        String playerName = "Player";
        StatusBar statusBar = new StatusBar(playerName);
         
//        create tiger onScreenss
        BlackTiger tiger1 = new BlackTiger();
        tiger1.setPosition(300, 300);
        
        Enemy.BadHuman.generatelistBot(5);
        BadHuman bad1 = Enemy.BadHuman.generateRandom();
        bad1.setPosition(300, 300);
        
        //enemySprite.add(bad1);
        enemySprite.addAll(Enemy.BadHuman.getbadList());

        Music.playBackGround();
        lastNanoTime = System.nanoTime();
        

        Timer timerBar = new Timer();
        Scoreboard scoreBoard = new Scoreboard();
        

        // Input
        this.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) ) {
                            input.add( code );
//                            System.out.println(code);
                        }
                        if(type2Key.contains(code) && !input2.contains(code)) {
                        	input2.add(code);
                        }
                    }
                });

        this.setOnKeyReleased(
        		new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if(input.contains(code)) {
                        	if(code.equals("SPACE")) {
                        		//try
//                        		statusBar.resetProgress();
                        		scoreBoard.addScore(100);
                        		tiger1.setCanMovePosition(true);
                        	}
                        	input.remove(code);
                        }
                        if(input2.contains(code)) {
                        	input2.remove(code);
                        }
                    }
                });
        root.getChildren().addAll( canvas,statusBar,timerBar,scoreBoard);
	}

}
