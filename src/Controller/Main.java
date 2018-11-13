package Controller;
	
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

<<<<<<< HEAD:src/application/Main.java
import Sprite.BlackTiger;
import Sprite.SpecialTiger;
import Sprite.TigerSprite;
=======
import TigerSpirte.tigerSprite;
import application.Images;
>>>>>>> 34dbf3a153f997c0a3c6c3eb1734167854cfee33:src/Controller/Main.java
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


	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Hello Tiger");
		Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

        Canvas canvas = new Canvas( 972, 600 );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(Images.startscreen, 0, 0);
        
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
                    }
                });

        theScene.setOnKeyReleased(
        		new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
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
                Main.keyActionToSpeed(tiger2);
                
                if(tiger1.getMove() == true) {        
                	tiger1.update(elapsedTime);
//                	if(currentNanoTime % 3 ==0) {
                		tiger1.setImage(tiger1.nextPosition());
//                	}
                }
                if(tiger2.getMove() == true) {
<<<<<<< HEAD:src/application/Main.java
                	tiger2.update(elapsedTime);
//                	if(currentNanoTime % 3 ==0) {
=======
                	tiger2.update(elapsedTime, tiger2.getDuration());
                	if(currentNanoTime % 4 ==0) {
>>>>>>> 34dbf3a153f997c0a3c6c3eb1734167854cfee33:src/Controller/Main.java
                		tiger2.setImage(tiger2.nextPosition());
//                	}
                }
//                if(tiger2.getDuration() == 0) tiger2.setDuration(5);
//                System.out.print
                gc.clearRect(0, 0, 972,800);
                
//                System.out.println(tiger1.getPositionY());
                gc.drawImage(Images.startscreen, 0, 0);
                tiger1.render( gc );
                tiger2.render(gc);
			}
        	
        }.start();
        primaryStage.show();
	}
	public static void keyActionToSpeed(TigerSprite tiger) {
		System.out.print("The position of the "+ tiger.getClass().getName());
		System.out.print("x:"+Double.toString(tiger.getPositionX()));
		System.out.println("y:"+Double.toString(tiger.getPositionY()));

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
	public static void main(String[] args) {
		launch(args);
	}
}
