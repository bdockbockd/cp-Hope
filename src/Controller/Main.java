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
    public static ArrayList<String> input = new ArrayList<String>();
    public static final ArrayList<String> type2Key = new ArrayList<String>();
    public static ArrayList<String> input2 = new ArrayList<String>();
    public static ArrayList<Sprite> enemySprite = new ArrayList<Sprite>();
    public static boolean canUpdateBot = true;
    public static boolean ccheck = true;
    public static GraphicsContext gc;
    public static LoopGame Loop;
    public static BlackTiger tiger1 = new BlackTiger();
    
    public ArrayList<BadHuman> bad = new ArrayList<BadHuman>();
    
    static {
    	type2Key.add("W");
    	type2Key.add("A");
    	type2Key.add("S");
    	type2Key.add("D");
    }
    


	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("blackPantherX");
		Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

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
        

        // Input
        theScene.setOnKeyPressed(
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

        theScene.setOnKeyReleased(
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
        
        
        Loop = new LoopGame();

		primaryStage.show();
//        primaryStage.setFullScreen(true);

	}
	public static void keyActionToSpeed(BlackTiger tiger, long current, GraphicsContext gc) {
//		System.out.print("The position of the "+ tiger.getClass().getName());
//		System.out.print("x:"+Double.toString(tiger.getPositionX()));
//		System.out.println("y:"+Double.toString(tiger.getPositionY()));
		if (input.contains("LEFT") && tiger.getPositionX() >-70) {
			// x 70
            tiger.addVelocity(-200,0);
            tiger.setFace("LEFT");
        }
        if (input.contains("RIGHT") && tiger.getPositionX() < 965) {
            tiger.addVelocity(200,0);
            tiger.setFace("RIGHT");
        }
        if (input.contains("UP") && tiger.getPositionY() > 150) {
        	// y 50
            tiger.addVelocity(0,-200);

        }
        if (input.contains("DOWN") && tiger.getPositionY() < 560) {
            tiger.addVelocity(0,200);
        }
        if(input.contains("SPACE") && tiger.isCanMovePosition() == true) {
			Music.attackSound();
			tiger.attackEnemy();

        	Thread t = new Thread(()->{
    			try {
    	        	tiger.setAttackable(true);
    	        	tiger.setFace(tiger.getFace());
    	        	tiger.nextPosition(tiger.getFace());
//    	        	System.out.print(tiger.getFace());
    	            tiger.setCanMovePosition(false);
    	            Thread.sleep(300);
    	            tiger.switchToWalk();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		});
    		t.start();
        }
	}
	
	public static void keySpeed(BadHuman bad, long current) {
//		System.out.print("The position of the "+ bad.getClass().getName());
//		System.out.print("x:"+Double.toString(bad.getPositionX()));
//		System.out.println("y:"+Double.toString(bad.getPositionY()));
		if (input.contains("A") && bad.getPositionX() >100) {
            bad.addVelocity(-200,0);
            bad.setFace("LEFT");
        }
		
        if (input.contains("D") && bad.getPositionX() < 1270) {
            bad.addVelocity(200,0);
            bad.setFace("RIGHT");
        }
        
        if (input.contains("W") && bad.getPositionY() > 312) {
            bad.addVelocity(0,-200);
        }
        
        if (input.contains("S") && bad.getPositionY() < 720) {
            bad.addVelocity(0,200);
        }
//        if(input.contains("H")) {
//        	bad.setAttackable(true);
//        }
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
}
