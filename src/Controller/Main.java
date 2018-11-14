package Controller;
	
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

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
        //        tiger1.setImage(); already set when declaring
        tiger1.setPosition(300, 300);
        
        SpecialTiger tiger2 = new SpecialTiger();
        tiger2.setPosition(400,400);
        
        
        

        
        lastNanoTime = System.nanoTime();

        String musicFile = "Music-loop-120-bpm.mp3";     // For example
        URL a= ClassLoader.getSystemResource(musicFile);
        Media b = new Media(a.toString());
        MediaPlayer ne = new MediaPlayer(b);
        ne.setAutoPlay(true);
        ne.setVolume(0.5);
        
        // Input
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
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
                        	input.remove(code);
                        }
                        if(input2.contains(code)) {
                        	input2.remove(code);
                        }
                    }
                });
        
        
//        for(int i =0; i<Images.bigTigerMotion.length;i++) {
//        	gc.drawImage((Images.bigTigerMotion)[i], );
//        }
        
        root.getChildren().add( canvas );
        new AnimationTimer()  {

			@Override
			public void handle(long currentNanoTime) {
				// TODO Auto-generated method stub
				// calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

                lastNanoTime = currentNanoTime;
                
                tiger1.setMove(false);
                tiger2.setMove(false);
                tiger1.setVelocity(0,0);
                tiger2.setVelocity(0, 0);
                
                Main.keyActionToSpeed(tiger1);
                Main.keyActionToSpeed2(tiger2);
                
                if(tiger1.getMove() == true) {        
                	tiger1.update(elapsedTime);
                	tiger1.setImage(tiger1.nextPosition());
                }
                if(tiger2.getMove() == true) {
                	tiger2.update(elapsedTime);
//                	if(currentNanoTime % 4 ==0) {
                		tiger2.setImage(tiger2.nextPosition());
//                }
                }
//                gc.clearRect(0, 0, 1250,800);
                System.out.print(tiger1.printBoundary());
                System.out.println(tiger2.printBoundary());
                if(tiger1.intersects(tiger2)) {
                	System.out.println("collide");
                } else {
                	System.out.println("not collide");
                }
                
                
				gc.drawImage((Images.stageMap)[(i+1)%3], 0, 0);
                i++;
                tiger1.render( gc );
                tiger2.render(gc);
                
			}	
        }.start();
		primaryStage.show();
//        primaryStage.setFullScreen(true);

	}
	public static void keyActionToSpeed(TigerSprite tiger) {
//		System.out.print("The position of the "+ tiger.getClass().getName());
//		System.out.print("x:"+Double.toString(tiger.getPositionX()));
//		System.out.println("y:"+Double.toString(tiger.getPositionY()));

		if (input.contains("LEFT") && tiger.getPositionX() >=50) {
            tiger.addVelocity(-100,0);
        }
        if (input.contains("RIGHT") && tiger.getPositionX() < 850) {
            tiger.addVelocity(100,0);

        }
        if (input.contains("UP") && tiger.getPositionY() > 250) {
            tiger.addVelocity(0,-100);

        }
        if (input.contains("DOWN") && tiger.getPositionY() < 500) {
            tiger.addVelocity(0,100);
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
	public static void main(String[] args) {
		launch(args);
	}
}
