package Controller;
	
import java.util.ArrayList;

import Enemy.*;
import Sprite.*;
import application.Images;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	private long lastNanoTime;
//	private AudioClip sound;
    public static ArrayList<String> input = new ArrayList<String>();
    public static final ArrayList<String> type2Key = new ArrayList<String>();
    public static ArrayList<String> input2 = new ArrayList<String>();
    public static ArrayList<Sprite> enemySprite = new ArrayList<Sprite>();
    public static boolean canUpdateBot = true;
    
//    public ArrayList<BadHuman> bad = new ArrayList<BadHuman>();
    
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
        

        
        
        //create tiger onScreenss
        BlackTiger tiger1 = new BlackTiger();
        tiger1.setPosition(300, 300);
        
        Enemy.BadHuman.generatelistBot(5);
        BadHuman bad1 = Enemy.BadHuman.generateRandom();
        bad1.setPosition(300, 300);
//        enemySprite.add(bad1);
        enemySprite.addAll(Enemy.BadHuman.getbadList());

        
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
                        		tiger1.setCanMovePosition(true);

                        		
                        	}
                        	input.remove(code);
                        }
                        if(input2.contains(code)) {
                        	input2.remove(code);
                        }
                    }
                });
        root.getChildren().add( canvas );
//        Label a = new Label("asdadasdasdas");
//        a.setAlignment(Pos.CENTER);
//        root.getChildren().add(a);
        
        
        
        new AnimationTimer()  {

			@Override
			public void handle(long currentNanoTime) {
				// TODO Auto-generated method stub
				// calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

                lastNanoTime = currentNanoTime;
                
                tiger1.setMove(false);
                tiger1.setVelocity(0,0);
                bad1.setVelocity(0, 0);
                Main.keyActionToSpeed(tiger1, currentNanoTime, gc);
            	Main.keySpeed(bad1, currentNanoTime);

            	bad1.update(elapsedTime);
//            	for(int i =0;i<BadHuman.getbadList().size();i++) {
//					((BadHuman.getbadList()).get(i)).update(elapsedTime);
//				}
//                if(tiger1.getMove() == true) {        
                	tiger1.update(elapsedTime);
//                	System.out.println(tiger1.getFace());
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
				if(Main.canUpdateBot == true) {
					Thread delay = new Thread(()->{
						try {
							for(int i =0;i<BadHuman.getbadList().size();i++) {
								((BadHuman.getbadList()).get(i)).update(elapsedTime, tiger1);
							}
							Main.canUpdateBot = false;
							Thread.sleep(1000);
							Main.canUpdateBot = true;
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					delay.start();
				}
				for(int i =0;i<BadHuman.getbadList().size();i++) {
					((BadHuman.getbadList()).get(i)).update(elapsedTime);
				}
			
				
				for(int i =0;i<BadHuman.getbadList().size();i++) {
					((BadHuman.getbadList()).get(i)).render(gc);
				}
				for(int i =0;i<Main.enemySprite.size();i++) {
					if(tiger1.intersect(Main.enemySprite.get(i))) {
//						System.out.println("got enemy");
					} 
				}
				bad1.render(gc);
				tiger1.render( gc );
			}	
        }.start();
		primaryStage.show();
//        primaryStage.setFullScreen(true);

	}
	public static void keyActionToSpeed(BlackTiger tiger, long current, GraphicsContext gc) {
//		System.out.print("The position of the "+ tiger.getClass().getName());
//		System.out.print("x:"+Double.toString(tiger.getPositionX()));
//		System.out.println("y:"+Double.toString(tiger.getPositionY()));
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
        if(input.contains("H") && tiger.isCanMovePosition() == true) {
			Main.playSound("32_udyr_tigerattack_roar_1.wav");

        	Thread t = new Thread(()->{
    			try {
    	        	tiger.setAttackable(true);
    	        	tiger.setFace(tiger.getFace());
//    	        	System.out.print(tiger.getFace());
    	            tiger.setCanMovePosition(false);
    	            Thread.sleep(500);
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
		if (input.contains("LEFT") && bad.getPositionX() >100) {
            bad.addVelocity(-200,0);
            bad.setFace("LEFT");
        }
		
        if (input.contains("RIGHT") && bad.getPositionX() < 1270) {
            bad.addVelocity(200,0);
            bad.setFace("RIGHT");
        }
        
        if (input.contains("UP") && bad.getPositionY() > 312) {
            bad.addVelocity(0,-200);
        }
        
        if (input.contains("DOWN") && bad.getPositionY() < 720) {
            bad.addVelocity(0,200);
        }
//        if(input.contains("H")) {
//        	bad.setAttackable(true);
//        }
	}
	
	
	public static void  playSound(String file) {
//		Media b = new Media("resources/BlackPantherDesign/greenBlackPanther/attack"+file);
//		MediaPlayer ne = new MediaPlayer(b);
//		ne.setVolume(0.5); 
//		ne.play();
		AudioClip sound = new AudioClip(ClassLoader.getSystemResource(file).toString());
		sound.setVolume(0.5);
		sound.play();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
