package application;

import java.util.ArrayList;

import Controller.Main;
import Enemy.BadHuman;
import Sprite.BlackTiger;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class LoopGame {
	public static BlackTiger tiger1 = Main.tiger1;
	public static GraphicsContext gc = Main.gc;
	private static long lastNanoTime = System.nanoTime();
    public static ArrayList<String> input = new ArrayList<String>();
    public static final ArrayList<String> type2Key = new ArrayList<String>();
    public static ArrayList<String> input2 = new ArrayList<String>();


    static {
    	type2Key.add("W");
    	type2Key.add("A");
    	type2Key.add("S");
    	type2Key.add("D");
    }
	
	public LoopGame(Scene theScene) {
		
		LoopGame.setKey(theScene);
		new AnimationTimer()  {
        	
			@Override
			public void handle(long currentNanoTime) {
		        // Input
		       
				// TODO Auto-generated method stub
				// calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

                lastNanoTime = currentNanoTime;
                
                tiger1.setMove(false);
                tiger1.setVelocity(0,0);
//                bad1.setVelocity(0, 0);
                LoopGame.keyActionToSpeed(tiger1, currentNanoTime, gc);
//            	Main.keySpeed(bad1, currentNanoTime);

//            	bad1.update(elapsedTime);
                tiger1.update(elapsedTime);
                
//                
                if(Main.ccheck) {
                Thread x = new Thread (()-> {
                	try {
                		Main.ccheck = false;
                		tiger1.nextPosition(tiger1.getFace());
                		Thread.sleep(50);
                		Main.ccheck = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                });
                x.start();
                }
               
                
				gc.drawImage((Images.stageMap)[0], 0, 0);
				if(Main.canUpdateBot == true && BadHuman.getbadList().size() != 0) {
					Thread delay = new Thread(()->{
						try {
							
								for(int i =0;i<BadHuman.getbadList().size();i++) {
									((BadHuman.getbadList()).get(i)).update(elapsedTime, tiger1);
								}
							
							
//							bad1.printBoundary();
//							tiger1.printBoundary();
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
//				gc.drawImage(Images.enemyTomb, 300, 300);

				BadHuman.checkAttackHuman(tiger1);
				for(int i =0;i<BadHuman.getbadList().size();i++) {
					
					((BadHuman.getbadList()).get(i)).update(elapsedTime);
				}
				
				
				for(int i =0;i<BadHuman.getbadList().size();i++) {
					((BadHuman.getbadList()).get(i)).render(gc);
				}
//				for(int i =0;i<Main.enemySprite.size();i++) {
//					if(tiger1.intersect(Main.enemySprite.get(i))) {
//						System.out.println("got enemy"+ i);
//					} 
//				}
				BadHuman.removeEnemy();
//				bad1.render(gc);
				tiger1.render( gc );
			}	
        }.start();
	}
	public static void keyActionToSpeed(BlackTiger tiger, long current, GraphicsContext gc) {

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
	
	public static void startGame() {
		
	}
	
	public static void setKey(Scene theScene) {
		 theScene.setOnKeyPressed(
	                new EventHandler<KeyEvent>()
	                {
	                    public void handle(KeyEvent e)
	                    {
	                        String code = e.getCode().toString();
	                        if ( !input.contains(code) ) {
	                            input.add( code );
//	                            System.out.println(code);
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
//	                        		statusBar.resetProgress();
//	                        		scoreBoard.addScore(100);
	                        		tiger1.setCanMovePosition(true);

	                        		
	                        	}
	                        	input.remove(code);
	                        }
	                        if(input2.contains(code)) {
	                        	input2.remove(code);
	                        }
	                    }
	                });
	}
}
