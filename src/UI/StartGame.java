package UI;

import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Controller.LoopGame;
import Controller.ScoreBoard;
import Controller.StatusBar;
import Controller.Timer;
import Enemy.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class StartGame extends Scene {

	public static Group root;

    public static GraphicsContext gc;
    public static Canvas canvas;
    public static LoopGame Loop;
    public static String playerName;
    
    //start new game
    private static Timer timerBar;
    private static ScoreBoard scoreBoard;
    private static StatusBar statusBar;
    
	public StartGame(String playerName) {
		super(new Group(), 1250, 800);
		root = (Group) super.getRoot();
		StartGame.playerName = playerName;
		timerBar = new Timer();
		scoreBoard = new ScoreBoard();
		statusBar = new StatusBar(playerName);

		Canvas canvas = new Canvas(1250, 800);
        gc = canvas.getGraphicsContext2D();
        gc.drawImage((Images.stageMap)[0], 0, 0);
                
        EnemyGen.generatelistBot(EnemyGen.ENEMYSTARTNUMBER);
        HumanSprite bad1 = EnemyGen.generateRandom();
        bad1.setPosition(1250, 800);
        
        Audio.GAME_BGM.play();

		root.getChildren().addAll(canvas, statusBar, timerBar, scoreBoard);
		Loop = new LoopGame(gc, this, playerName);
	}

}
