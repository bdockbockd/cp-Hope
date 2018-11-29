package Controller;
	
import java.io.*;
import sun.audio.*;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;

import Sprite.BlackTiger;
import Sprite.SpecialTiger;
import Sprite.TigerSprite;
import application.Images;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {
	private long lastNanoTime;
//	private AudioClip sound;
    public static ArrayList<String> input = new ArrayList<String>();
    public static final ArrayList<String> type2Key = new ArrayList<String>();
    public static ArrayList<String> input2 = new ArrayList<String>();
    
    static {
    	type2Key.add("W");
    	type2Key.add("A");
    	type2Key.add("S");
    	type2Key.add("D");
    }
    int i;



	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Hello Tiger");
		Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

        Canvas canvas = new Canvas(1250,800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage((Images.stageMap)[0], 0, 0);
        
        //create tiger onScreen
        BlackTiger tiger1 = new BlackTiger();
        tiger1.setPosition(300, 300);
        
        lastNanoTime = System.nanoTime();

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
                        	if(code.equals("H")) {
                        		tiger1.setAttackable(false);
                        		tiger1.render(gc);
                        	}
                        	input.remove(code);
                        }
                        if(input2.contains(code)) {
                        	input2.remove(code);
                        }
                    }
                });
        
        root.getChildren().add( canvas );
        new AnimationTimer()  {

			@Override
			public void handle(long currentNanoTime) {
				// TODO Auto-generated method stub
				// calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

                lastNanoTime = currentNanoTime;
                
                tiger1.setMove(false);
                tiger1.setVelocity(0,0);
                Main.keyActionToSpeed(tiger1, currentNanoTime);
                
//                if(tiger1.getMove() == true) {        
                	tiger1.update(elapsedTime);
                	tiger1.nextPosition(tiger1.getFace());
//                }
                
//                gc.clearRect(0, 0, 1250,800);
//                System.out.print(tiger1.printBoundary());
                
//                System.out.print(tiger1.printBoundary());
//                System.out.println(tiger2.printBoundary());
                	
//                if(tiger1.intersects(tiger2)) {
//                	System.out.println("collide");
//                } else {
//                	System.out.println("not collide");
//                }
                
                
				gc.drawImage((Images.stageMap)[0], 0, 0);
//                i++;
				tiger1.render( gc );
			}	
        }.start();
		primaryStage.show();
//        primaryStage.setFullScreen(true);

	}
	public static void keyActionToSpeed(TigerSprite tiger, long current) {
		System.out.print("The position of the "+ tiger.getClass().getName());
		System.out.print("x:"+Double.toString(tiger.getPositionX()));
		System.out.println("y:"+Double.toString(tiger.getPositionY()));
		if (input.contains("LEFT") && tiger.getPositionX() >-55) {
            tiger.addVelocity(-200,0);
            tiger.setFace("LEFT");
        }
        if (input.contains("RIGHT") && tiger.getPositionX() < 965) {
            tiger.addVelocity(200,0);
            tiger.setFace("RIGHT");
        }
        if (input.contains("UP") && tiger.getPositionY() > 150) {
            tiger.addVelocity(0,-200);

        }
        if (input.contains("DOWN") && tiger.getPositionY() < 640) {
            tiger.addVelocity(0,200);
        }
        if(input.contains("H")) {
        	tiger.setAttackable(true);
            Main.playSound("32_udyr_tigerattack_roar_1.wav");
        }
	}
	public static void keyActionToSpeed2(TigerSprite tiger) {
//		System.out.print("The position of the "+ tiger.getClass().getName());
//		System.out.print("x:"+Double.toString(tiger.getPositionX()));
//		System.out.println("y:"+Double.toString(tiger.getPositionY()));

		if (input2.contains("A") && tiger.getPositionX() >=50) {
            tiger.addVelocity(-100,0);
            
        }
        if (input2.contains("D") && tiger.getPositionX() < 850) {
            tiger.addVelocity(100,0);

        }
        if (input2.contains("W") && tiger.getPositionY() > 250) {
            tiger.addVelocity(0,-100);

        }
        if (input2.contains("S") && tiger.getPositionY() < 500) {
            tiger.addVelocity(0,100);
        }
	}
	
	public static void  playSound(String file) {
		Media b = new Media("resources/BlackPantherDesign/greenBlackPanther/attack"+file);
		MediaPlayer ne = new MediaPlayer(b);
		ne.setVolume(0.5); 
		ne.play();
		AudioClip sound = new AudioClip(ClassLoader.getSystemResource(file).toString());
		sound.setVolume(0.5);
		sound.play();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
