package Controller;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Constant.Audio;
import Constant.Images;
import Enemy.BadHuman;
import Exception.GameOverException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class LoopGame {
	public static BlackPanther blackPanther;
	private static long lastNanoTime ;
    public static ArrayList<String> input;
    public static final ArrayList<String> type2Key = new ArrayList<String>();
    public static ArrayList<String> input2;
    public static GamePause gamePause;
    public static DeadScene deadScene;
    public static boolean isDead;
    public static boolean BOTSPAWN = true;
    public static final int BOTSPAWNRATE = 3; //BOTPERSEC
    public static boolean CANUPDATEBOT;
    public static boolean CCHECK;
    public static double elapsedTime;
    public static GraphicsContext gc;
    public static Thread DELAYBOT;
    public static String playerName;

    public static final String ATTACK_KEY = "Z";
    public static final String JUMP_KEY = "X";
    public static final String SPIN_KEY = "C";
    public static final String GODMODE_ON_KEY = "I";
    public static final String GODMODE_OFF_KEY = "O";
    public static final String GETSCORE_KEY = "P";
	
	public LoopGame(GraphicsContext gc, Scene theScene, String playerName) {
		LoopGame.playerName = playerName;
		LoopGame.gc = gc;
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
//	
		new AnimationTimer()  {
			@Override
			public void handle(long currentNanoTime) {
				if(!isDead && !GamePause.isPause) {
					if(BOTSPAWN)
					{
						Thread addBot = new Thread(()->{
							try {
//								BadHuman.addBot();
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
						if(CCHECK && blackPanther.getActionState() == 0) {
							Thread x = new Thread (()-> {
								try {
									CCHECK = false;
									blackPanther.nextPosition(blackPanther.getFace());
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
						
						DELAYBOT = new Thread(()->{
							try {
								for(int i =0;i<BadHuman.getbadList().size();i++) {
									if(!BadHuman.getbadList().get(i).isDead()) {
										((BadHuman.getbadList()).get(i)).update(elapsedTime, blackPanther);
									}
								}
								CANUPDATEBOT = false;
								Thread.sleep(3000);
								CANUPDATEBOT = true;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						});
						DELAYBOT.start();
						
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
					Collections.sort(BadHuman.getbadList(), new Comparator<BadHuman>() {
			            @Override
			            public int compare(final BadHuman o1, final BadHuman o2) {
			            	if (o1.getPositionY() < o2.getPositionY()) {
			                    return -1;
			                } else {
			                    return 1;
			                }
			            }
			        });
					for(int i =0;i<BadHuman.getbadList().size();i++) {
						((BadHuman.getbadList()).get(i)).render(StartGame.gc);
					}
					
//					blackPanther.printBoundary();
					//render tiger	
					bad1.render(gc);
					blackPanther.render(gc);
					Controller.ScoreBoard.update();
					Controller.StatusBar.resetProgress(blackPanther);
					Item.checkItemUse(blackPanther);
					blackPanther.checkStatus();
					Item.render(gc);
				
					//Game Over
					try {
						gameOverCheck();
					} catch (GameOverException e) {
						//stop ever thing must be implement here!
						deadScene = new DeadScene(playerName,ScoreBoard.getScore(),Timer.getString());
						deadScene.show(Main.stage);
						//e.printStackTrace();
					}
				}
			}
        }.start();

	}
	
	protected static void gameOverCheck() throws GameOverException {
		if(blackPanther.isDead()) {
			isDead = true;
			Audio.GAME_BGM.stop();
			Audio.DEAD.play(1);
			Timer.stop();
			Timer.hide();
			Timer.terminate();
			ScoreBoard.hide();
			ScoreBoard.addScore(Timer.getSec()*1000);
			throw new GameOverException();
		}	
	}
	
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
		
		if(input.contains(SPIN_KEY) && BlackPanther.spinAttackDetected == false) {
			Audio.spinSound();
			tiger.attackEnemy();
			
			Thread delay = new Thread(()-> {
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
				BlackPanther.spinAttackDetected = false;
				blackPanther.switchToWalk();
			});
			delay.start();
			
		} 
		
		if(input.contains(GODMODE_ON_KEY)) {
			blackPanther.enableGodMode();
			System.out.println("enableGodMode!");
		}
		else if(input.contains(GODMODE_OFF_KEY)) {
			blackPanther.disableGodMode();
			System.out.println("disableGodMode!");
		}
		if(input.contains(GETSCORE_KEY)) {
			ScoreBoard.addScore(10000);
		}
		

		if(input.contains(JUMP_KEY) && BlackPanther.jumpAttackDetected == false) {
		
			BlackPanther.jumpAttackDetected = true;
			blackPanther.setSpeedFix(true);
			blackPanther.setActionState(3);
			String direction = tiger.getFace();
			tiger.setFace(direction);
			tiger.nextPosition(direction);
			tiger.playJump(direction);
			Audio.pounceSound();
			tiger.attackEnemy();
			//set Speed Fix in here
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
        if(input.contains(ATTACK_KEY) && tiger.isCanMovePosition() == true) {
			Audio.attackSound();
			tiger.attackEnemy();

        	Thread t = new Thread(()->{
    			try {
    				//setImageList
    	        	tiger.setAttackable(true);
    	        	//setImage
    	        	tiger.setFace(tiger.getFace());
    	        	//movePosition
    	        	tiger.nextPosition(tiger.getFace());
    	            tiger.setCanMovePosition(false);
    	            Thread.sleep(500);
    	            tiger.setCanMovePosition(true);
    	            tiger.setActionState(0);
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
	                        	if(code.equals(ATTACK_KEY)) {
	                        		//try
//	                        		statusBar.resetProgress();
//	                        		scoreBoard.addScore(100);
//	                        		blackPanther.setCanMovePosition(true);
	
	                        	} else if(code.equals(SPIN_KEY)) {
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
