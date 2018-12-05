package application;

import java.util.ArrayList;

import Controller.Main;
import Controller.StartGame;
import Enemy.BadHuman;
import Sprite.BlackTiger;
import Sprite.HealthPotion;
import Sprite.Meat;
import Sprite.Item;
import Sprite.SuperPotion;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoopGame {
	public static BlackTiger tiger1 = StartGame.tiger1;
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
		// scene detect
		LoopGame.setKey(theScene);
		BadHuman bad1 = new BadHuman();
		// item test
		Meat meat = new Meat(20,20+220);
		HealthPotion healthPotion = new HealthPotion(900,20+220);
		SuperPotion superPotion = new SuperPotion(400,220+20);
		new AnimationTimer()  {
        	
			@Override
			public void handle(long currentNanoTime) {
		        // Input
                //drawMap
				// TODO Auto-generated method stub
				// calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

                lastNanoTime = currentNanoTime;
                
                // set Velocity tiger
                bad1.setVelocity(0, 0);
                tiger1.setVelocity(0,0);
                 
                //update velocity tiger and detect attack hit
                LoopGame.keyActionToSpeed(tiger1, currentNanoTime, this);
//            	LoopGame.keySkill(tiger1, StartGame.gc);

            	LoopGame.keySpeed(bad1, currentNanoTime);

            	bad1.update(elapsedTime);
                //update position from time and velocity
                tiger1.update(elapsedTime);

//              // change Position tiger
                if(StartGame.ccheck) {
                Thread x = new Thread (()-> {
                	try {
                		StartGame.ccheck = false;
                		tiger1.nextPosition(tiger1.getFace());
                		Thread.sleep(50);
                		StartGame.ccheck = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                });
                x.start();
                }
               
                // updateBot every 1 sec
				if(StartGame.canUpdateBot == true && BadHuman.getbadList().size() != 0 ) {
					Thread delay = new Thread(()->{
						try {
							
								for(int i =0;i<BadHuman.getbadList().size();i++) {
									if(!BadHuman.getbadList().get(i).isDead()) {
										((BadHuman.getbadList()).get(i)).update(elapsedTime, tiger1);
									}
								}
							
							StartGame.canUpdateBot = false;
							Thread.sleep(1000);
							StartGame.canUpdateBot = true;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					delay.start();
				}
				
				// check bot attack
				BadHuman.checkAttackHuman(tiger1);
				// check bot get damaged
				BadHuman.removeEnemy();

				for(int i =0;i<BadHuman.getbadList().size();i++) {
					
					((BadHuman.getbadList()).get(i)).update(elapsedTime);
				}
				//remove bot
				
				StartGame.gc.drawImage((Images.stageMap)[0], 0, 0);

				// render bot
				for(int i =0;i<BadHuman.getbadList().size();i++) {
					((BadHuman.getbadList()).get(i)).render(StartGame.gc);
				}
//				tiger1.printBoundary();
				//render tiger
				bad1.render(StartGame.gc);
				tiger1.render( StartGame.gc );
				Controller.ScoreBoard.update();
				Controller.StatusBar.resetProgress(tiger1);
				Sprite.Item.render(StartGame.gc);
				Sprite.Item.checkItemUse(tiger1);
			}	
        }.start();
	}
	protected static void keySkill(BlackTiger tiger1, GraphicsContext gc) {
		// TODO Auto-generated method stub
	
		
	}
	protected static void keySpeed(BadHuman bad1, long currentNanoTime) {
		// TODO Auto-generated method stub
//		bad1.printBoundary();
		if (input.contains("A")) {
			// x 70
            bad1.addVelocity(-200,0);
            bad1.setFace("LEFT");
        }
        if (input.contains("D")) {
            bad1.addVelocity(200,0);
            bad1.setFace("RIGHT");
        }
        if (input.contains("W")) {
        	// y 50
            bad1.addVelocity(0,-200);

        }
        if (input.contains("S")) {
            bad1.addVelocity(0,200);
        }
		
	}
	public static void keyActionToSpeed(BlackTiger tiger, long current, AnimationTimer x) {
		if(input.contains("ESCAPE")){
			try {
				x.wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(input.contains("X") && BlackTiger.spinAttackDetected == false) {
			Audio.spinSound();
			tiger.attackEnemy();
			
			Thread delay = new Thread(()-> {
				BlackTiger.spinAttackDetected = true;
				tiger1.setActionState(2);
				tiger1.setFace(tiger1.getFace());
				tiger1.nextPosition(tiger.getFace());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BlackTiger.spinAttackDetected = false;
				tiger1.switchToWalk();
			});
			delay.start();
			
		} else if(input.contains("C") && BlackTiger.jumpAttackDetected == false) {
			Audio.pounceSound();
			tiger.attackEnemy();
			
			Thread delay = new Thread(()-> {
				BlackTiger.jumpAttackDetected = true;
				tiger1.setActionState(3);
				tiger1.setFace(tiger1.getFace());
				tiger1.nextPosition(tiger1.getFace());
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BlackTiger.spinAttackDetected = false;
				tiger1.switchToWalk();
				

			});
			delay.start();
			
		}

		if (input.contains("LEFT") && tiger.getRealX() > 0) {
			// x 70
            tiger.addVelocity(-200,0);
            tiger.setFace("LEFT");
        }
        if (input.contains("RIGHT") && tiger.getRealX() < 1230 - tiger.getRealWidth()) {
            tiger.addVelocity(200,0);
            tiger.setFace("RIGHT");
        }
        if (input.contains("UP") && tiger.getRealY() > 210) {
        	// y 50
            tiger.addVelocity(0,-200);

        }
        if (input.contains("DOWN") && tiger.getRealY() < 800-tiger.getRealHeight()) {
            tiger.addVelocity(0,200);
        }
        if(input.contains("SPACE") && tiger.isCanMovePosition() == true) {
			Audio.attackSound();
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
	                        if(e.getCode() == KeyCode.ESCAPE)
	                        {
	                        	Main.gamePause();
	                        }
	                    }
	                });

	        theScene.setOnKeyReleased(
	        		new EventHandler<KeyEvent>()
	                {
	                    public void handle(KeyEvent e)
	                    {
	                        String code = e.getCode().toString();
	                        System.out.print(code);
	                        if(input.contains(code)) {
	                        	if(code.equals("SPACE")) {
	                        		//try
//	                        		statusBar.resetProgress();
//	                        		scoreBoard.addScore(100);
	                        		tiger1.setCanMovePosition(true);
	
	                        	} else if(code.equals("C")) {
	                        		BlackTiger.jumpAttackDetected = false;
	                        	} else if(code.equals("X")) {
	                        		BlackTiger.spinAttackDetected = false;
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
