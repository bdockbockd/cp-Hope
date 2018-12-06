

import java.sql.Time;
import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import Controller.*;
import Enemy.BadHuman;
import Sprite.BlackTiger;
import Sprite.HealthPotion;
import Sprite.Meat;
import Sprite.Item;
import Sprite.SuperPotion;
import Item.HealthPotion;
import Item.Item;
import Item.Meat;
import Item.SuperPotion;
import Sprite.BlackPanther;
import UI.DeadScene;
import UI.GamePause;
import UI.StartGame;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoopGame {
	public static BlackTiger tiger1;
	public static BlackPanther blackPanther;
	private static long lastNanoTime ;
    public static ArrayList<String> input;
    public static final ArrayList<String> type2Key = new ArrayList<String>();
    public static ArrayList<String> input2;
    public static GamePause gamePause;
    public static DeadScene deadScene;
    public static boolean isDead;
    public static boolean BOTSPAWN = true;
    public static boolean GODMODE = false;
    public static int BOTSPAWNRATE = 5; //BOTPERSEC
    public static boolean CANUPDATEBOT;
    public static boolean CCHECK;
    public static double elapsedTime;
    public static GraphicsContext gc;

    static {
    	type2Key.add("W");
    	type2Key.add("A");
    	type2Key.add("S");
    	type2Key.add("D");
    }
	
	public LoopGame(Scene theScene, String playerName) {
		tiger1 = StartGame.tiger1;
	public LoopGame(GraphicsContext gc, Scene theScene, String playerName) {
		this.gc = gc;
		blackPanther = new BlackPanther();
		blackPanther.setPosition(1250/2 - 351/2, 800/2+100);
		lastNanoTime = System.nanoTime();
		input = new ArrayList<String>();
		input2 = new ArrayList<String>();
		// scene detect
		LoopGame.setKey(theScene);
		BadHuman bad1 = new BadHuman();
		gamePause = new GamePause();
		isDead = false;
		CANUPDATEBOT = true;
		CCHECK = true;
		// item test
		Meat meat = new Meat(20,20+220);
		HealthPotion healthPotion = new HealthPotion(900,20+220);
		SuperPotion superPotion = new SuperPotion(400,220+20);
		new AnimationTimer()  {
			
			
			@Override
			public void handle(long currentNanoTime) {
				if(!isDead && !GamePause.isPause) {
					if(BOTSPAWN)
					{
						Thread addBot = new Thread(()->{
							try {
								BadHuman.addBot();
								BOTSPAWN = false;
								Thread.sleep(1000/BOTSPAWNRATE);
								BOTSPAWN = true;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						});
						addBot.start();
					}

					// Input
					//drawMap
					// TODO Auto-generated method stub
					// calculate time since last update.
					double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;

					elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
					if(elapsedTime > 0.02) {
						elapsedTime = 0.016;
					}
					//System.out.println(elapsedTime);
					lastNanoTime = currentNanoTime;
                
					// set Velocity tiger
					bad1.setVelocity(0, 0);
					if(!(blackPanther.isSpeedFix())) {
						blackPanther.setVelocity(0,0);
						LoopGame.keyActionToSpeed(blackPanther, currentNanoTime, this);

                    // checkPosition Tiger
						if(StartGame.ccheck && tiger1.getActionState() == 0) {
							Thread x = new Thread (()-> {
								try {
									StartGame.ccheck = false;	
									tiger1.nextPosition(tiger1.getFace());
									Thread.sleep(50);
									CCHECK = true;
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							});
							x.start();
                       	}
					}
                 
					//update velocity tiger and detect attack hit
//            		LoopGame.keySkill(blackPanther, StartGame.gc);

					LoopGame.keySpeed(bad1, currentNanoTime);

					bad1.update(elapsedTime);
					//update position from time and velocity	
					blackPanther.update(elapsedTime);
              
               
					// updateBot every 1 
					if(CANUPDATEBOT == true && BadHuman.getbadList().size() != 0) {
						Thread delay = new Thread(()->{
							try {
								for(int i =0;i<BadHuman.getbadList().size();i++) {
									if(!BadHuman.getbadList().get(i).isDead()) {
										((BadHuman.getbadList()).get(i)).update(elapsedTime, blackPanther);
									}
								}
								CANUPDATEBOT = false;
								Thread.sleep(1000);
								CANUPDATEBOT = true;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						});
						delay.start();
					}
				
					// check bot attack
					BadHuman.checkAttackHuman(blackPanther);
					// check bot get damaged
					BadHuman.removeEnemy();

					for(int i =0;i<BadHuman.getbadList().size();i++) {
						((BadHuman.getbadList()).get(i)).update(elapsedTime);
					}
					//remove bot
					
					gc.drawImage((Images.stageMap)[blackPanther.getStatus()], 0, 0);

					// render bot
					for(int i =0;i<BadHuman.getbadList().size();i++) {
						((BadHuman.getbadList()).get(i)).render(StartGame.gc);
					}
					
//					blackPanther.printBoundary();
					//render tiger	
					bad1.render(StartGame.gc);
					blackPanther.render( StartGame.gc );
					Controller.ScoreBoard.update();
					Controller.StatusBar.resetProgress(blackPanther);
					Item.render(StartGame.gc);
					Item.checkItemUse(blackPanther);
				
					//Game Over
					if(tiger1.isDead()) {
					if(blackPanther.isDead()&&!GODMODE) {
						isDead = true;
						Audio.SELECTMENU.play();
						System.out.println("GAME OVER!");
						Timer.stop();
						Timer.hide();
						Timer.terminate();
						ScoreBoard.hide();
						ScoreBoard.addScore(Timer.getSec()*1000);
						isDead = true;
						deadScene = new DeadScene(playerName,ScoreBoard.getScore(),Timer.getString());
						deadScene.show(Main.stage);
					
						//stop doing everthing
					}	
				}
			}
        }.start();
	}
	
	protected static void keySkill(BlackTiger tiger1, GraphicsContext gc) {
	protected static void keySkill(BlackPanther blackPanther, GraphicsContext gc) {
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
	public static void keyActionToSpeed(BlackPanther tiger, long current, AnimationTimer x) {
		
		if(input.contains("X") && BlackPanther.spinAttackDetected == false) {
			Audio.spinSound();
			tiger.attackEnemy();
			
			Thread delay = new Thread(()-> {
				BlackTiger.spinAttackDetected = true;
				tiger1.setActionState(2);
				tiger1.setFace(tiger1.getFace());
				tiger1.nextPosition(tiger.getFace());
				BlackPanther.spinAttackDetected = true;
				blackPanther.setActionState(2);
				blackPanther.setFace(blackPanther.getFace());
				blackPanther.nextPosition(tiger.getFace());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BlackTiger.spinAttackDetected = false;
				tiger1.switchToWalk();
				BlackPanther.spinAttackDetected = false;
				blackPanther.switchToWalk();
			});
			delay.start();
			
		} 

		if(input.contains("C") && BlackPanther.jumpAttackDetected == false) {
		
			tiger1.setActionState(3);
			BlackTiger.jumpAttackDetected = true;
			tiger1.setSpeedFix(true);
			blackPanther.setActionState(3);
			BlackPanther.jumpAttackDetected = true;
			blackPanther.setSpeedFix(true);
			tiger.setFace(tiger.getFace());

			Audio.pounceSound();
			tiger.attackEnemy();
			tiger.playJump();

		}
		if(input.contains("ESCAPE") && !gamePause.isShowing() && !GamePause.isPause){
			Audio.SELECTMENU.play();
			GamePause.isPause = true;
			Timer.stop();
			Timer.hide();
			ScoreBoard.hide();
			System.out.println("GAME IS PAUSED!");
			gamePause.show(Main.stage);
		}

	    if (input.contains("LEFT") && tiger.getRealX() > 0) {
			// x 70
            tiger.addVelocity(-200,0);
            tiger.setFace("LEFT");
            tiger.setActionState(0);
        }
	    if (input.contains("RIGHT") && tiger.getRealX() < 1230 - tiger.getRealWidth()) {
            tiger.addVelocity(200,0);
            tiger.setFace("RIGHT");
            tiger.setActionState(0);

        }
	    if (input.contains("UP") && tiger.getRealY() > 210) {
        	// y 50
            tiger.addVelocity(0,-200);
            tiger.setActionState(0);

        }
		else if (input.contains("DOWN") && tiger.getRealY() < 800-tiger.getRealHeight()) {
            tiger.addVelocity(0,200);
            tiger.setActionState(0);

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
	                        /*if(e.getCode() == KeyCode.ESCAPE)
	                        {
	                        	Main.gamePause();
	                        }*/
	                    }
	                });

	        theScene.setOnKeyReleased(
	        		new EventHandler<KeyEvent>()
	                {
	                    public void handle(KeyEvent e)
	                    {
	                        String code = e.getCode().toString();
	                        //System.out.print(code);
	                        if(input.contains(code)) {
	                        	if(code.equals("SPACE")) {
	                        		//try
//	                        		statusBar.resetProgress();
//	                        		scoreBoard.addScore(100);
	                        		blackPanther.setCanMovePosition(true);
	
	                        	} else if(code.equals("X")) {
	                        		BlackPanther.spinAttackDetected = false;
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
