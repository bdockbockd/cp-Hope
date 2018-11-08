package application;
	
import java.net.URL;
import java.util.ArrayList;

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
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private long lastNanoTime;
//	private AudioClip sound;

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
        tigerSprite tiger1 = new tigerSprite();
//        tiger1.setImage(); already set when declaring
        tiger1.setPosition(300, 300);
        
        tigerSprite tiger2 = new tigerSprite();
        tiger2.changeType();
        tiger2.setPosition(400,400);
        
        
        
        //sound 
//        sound = new AudioClip(this.getClass().getResource("bensound-buddy.mp3").toExternalForm());
//        sound.play();
//        URL resource = getClass().getClassLoader().getResource("bensound-buddy.mp3");
//        Media media = new Media(resource.toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
        
        lastNanoTime = System.nanoTime();

        
        // Input
        ArrayList<String> input = new ArrayList<String>();
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
                if (input.contains("LEFT") && tiger1.getPositionX() >=0) {
                    tiger1.addVelocity(-100,0);
                    tiger2.addVelocity(-100,0);

                }
                if (input.contains("RIGHT") && tiger1.getPositionX() < 850) {
                    tiger1.addVelocity(100,0);
                    tiger2.addVelocity(100,0);

                }
                if (input.contains("UP") && tiger1.getPositionY() > 250) {
                    tiger1.addVelocity(0,-100);
                    tiger2.addVelocity(0,-100);

                }
                if (input.contains("DOWN") && tiger1.getPositionY() < 500) {
                    tiger1.addVelocity(0,100);
                    tiger2.addVelocity(0,100);

                }
                if(tiger1.getMove() == true) {        
                	tiger1.update(elapsedTime);
                	tiger2.update(elapsedTime, "Go opposite Direction");
                }
                gc.clearRect(0, 0, 972,800);
                
//                System.out.println(tiger1.getPositionY());
                gc.drawImage(Images.startscreen, 0, 0);
                tiger1.render( gc );
                tiger2.render(gc);
			}
        	
        }.start();
        primaryStage.show();
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
