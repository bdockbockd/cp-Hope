package Controller;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.sun.javafx.tk.CompletionListener;
import com.sun.javafx.tk.RenderJob;

import Constant.Audio;
import Constant.Images;
import Enemy.BadHuman;
import Exception.GameOverException;
import Item.Item;
import Sprite.BlackPanther;
import UI.DeadScene;
import UI.GamePause;
import UI.StartGame;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class LoopGame {
	public static BlackPanther blackPanther;
	private static long lastNanoTime ;
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
    public static String playerName;
    public static boolean botHit;

    public static Thread DELAYBOT;

	public LoopGame(GraphicsContext gc, Scene theScene, String playerName) {
		
		
		LoopGame.startGame(gc,theScene,playerName);
		
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
					// calculate time since last update.

					elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
					if(elapsedTime > 0.02) {
						elapsedTime = 0.016;
					}
					//System.out.println(elapsedTime);
					lastNanoTime = currentNanoTime;
                
					// set Velocity tiger
					if(!(blackPanther.isSkillOn())) {
						blackPanther.setVelocity(0,0);
						KeyControlBlackPanther.keyActionToSpeed(blackPanther, currentNanoTime, this);

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
					
					//update position from time and velocity	
					blackPanther.update(elapsedTime);
              
               
					// updateBot random every 1 (VELOCITYXY)
					if(CANUPDATEBOT == true && BadHuman.getbadList().size() != 0) {
						
						DELAYBOT = new Thread(()->{
							try {
								for(int i =0;i<BadHuman.getbadList().size();i++) {
									if(!BadHuman.getbadList().get(i).isDead() && !(BadHuman.getbadList().get(i).isKnockBack())) {
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

					//update bot all times 
					for(int i =0;i<BadHuman.getbadList().size();i++) {
						if(BadHuman.getbadList().get(i).isKnockBack()) {
//							BadHuman.getbadList().get(i).KnockBack(blackPanther.getFace());
						} 
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
					
					//render tiger	
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
	
	public static void startGame(GraphicsContext gc, Scene theScene, String playerName) {
		LoopGame.playerName = playerName;
		LoopGame.gc = gc;
		blackPanther = new BlackPanther();
		blackPanther.setPosition(1250/2 - 351/2, 800/2+100);
		lastNanoTime = System.nanoTime();
		KeyControlBlackPanther.input = new ArrayList<String>();
		input2 = new ArrayList<String>();
		// scene detect
		LoopGame.setKey(theScene);
		gamePause = new GamePause();
		isDead = false;
		CANUPDATEBOT = true;
		CCHECK = true;
	}
	
	public static void setKey(Scene theScene) {

		
		
		 theScene.setOnKeyPressed(
	                new EventHandler<KeyEvent>()
	                {
	                    public void handle(KeyEvent e)
	                    {
	                        String code = e.getCode().toString();
	                        if ( !KeyControlBlackPanther.input.contains(code) ) {
	                            KeyControlBlackPanther.input.add( code );
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
	                        if(KeyControlBlackPanther.input.contains(code)) {
	                        	if(code.equals(KeyControlBlackPanther.ATTACK_KEY)) {
	                        		//try
//	                        		statusBar.resetProgress();
//	                        		scoreBoard.addScore(100);
	                        		blackPanther.switchToWalk();
	
	                        	} else if(code.equals(KeyControlBlackPanther.SPIN_KEY)) {
//	                        		BlackPanther.spinAttackDetected = false;
	                        	}
	                        	KeyControlBlackPanther.input.remove(code);
	                        }
	                        if(input2.contains(code)) {
	                        	input2.remove(code);
	                        }
	                    }
	                });
	}
}
