package Controller;

import java.util.ArrayList;

import Constant.Audio;
import Sprite.BlackPanther;
import UI.GamePause;
import javafx.animation.AnimationTimer;

public class KeyControlBlackPanther {
	
    public static ArrayList<String> input;
    public static ArrayList<String> directionInput;
    public final static ArrayList<String> DIRECTION_KEY = new ArrayList<String>();
    
    static {
    	DIRECTION_KEY.add("UP");
    	DIRECTION_KEY.add("DOWN");
    	DIRECTION_KEY.add("LEFT");
    	DIRECTION_KEY.add("RIGHT");
    }
    
    public static final String ATTACK_KEY = "SPACE";
    public static final String JUMP_KEY = "X";
    public static final String SPIN_KEY = "C";
    public static final String GODMODE_ON_KEY = "I";
    public static final String GODMODE_OFF_KEY = "O";
    public static final String GETSCORE_KEY = "P";

	public static void keyActionToSpeed(BlackPanther blackPanther, long current, AnimationTimer x) {
		
		if(input.contains(JUMP_KEY) && BlackPanther.jumpAttackDetected == false && StatusBar.pounceIsReady()) {
			blackPanther.setActionState(3);
			blackPanther.setLocked(false);
			BlackPanther.jumpAttackDetected = true;
			blackPanther.setSkillOn(true);
			String direction = blackPanther.getFace();
			blackPanther.playJump(direction);

//			blackPanther.playJump(KeyControlBlackPanther.getKeyCombination());
			Audio.pounceSound();
			blackPanther.attackEnemy(3);
			return;
			//set Speed Fix in here
		} else if(input.contains(SPIN_KEY) && BlackPanther.spinAttackDetected == false && StatusBar.spinIsReady()) {

			blackPanther.setActionState(2);
			blackPanther.attackEnemy(2);
			blackPanther.setLocked(false);
			Audio.spinSound();
			blackPanther.setSkillOn(true);

			Thread delay = new Thread(()-> {
				BlackPanther.spinAttackDetected = true;
				blackPanther.setFace(blackPanther.getFace());
				blackPanther.nextPosition(blackPanther.getFace());	
				try {
					int loop = 6;
					while(loop!=0) {
						blackPanther.update(0.016);
						blackPanther.attackEnemy(2);
						
						blackPanther.setVelocity(-1000, 0);
						Thread.sleep(30);
						blackPanther.setFace(blackPanther.getFace());
						blackPanther.attackEnemy(2);
						blackPanther.setActionState(2);
						blackPanther.nextPosition(blackPanther.getFace());
						blackPanther.setVelocity(1000, 0);
						Thread.sleep(30);
						blackPanther.setFace("OPPOSITE");
						blackPanther.attackEnemy(2);

						blackPanther.setActionState(2);

						blackPanther.nextPosition(blackPanther.getFace());	
		
						blackPanther.setVelocity(-1000, 0);
						
						Thread.sleep(30);
						blackPanther.setFace(blackPanther.getFace());
						blackPanther.attackEnemy(2);
						blackPanther.setActionState(2);

						blackPanther.nextPosition(blackPanther.getFace());
						blackPanther.setVelocity(1000, 0);
						Thread.sleep(30);
						blackPanther.setFace("OPPOSITE");
						blackPanther.attackEnemy(2);

//						blackPanther.setActionState(2);
						blackPanther.nextPosition(blackPanther.getFace());	

		        		loop--;
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BlackPanther.spinAttackDetected = false;
				blackPanther.setSkillOn(false);
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
		
		if(input.contains("ESCAPE") && !LoopGame.gamePause.isShowing() && !GamePause.isPause){
			Audio.SELECTMENU.play();
			GamePause.isPause = true;
			Timer.stop();
			Timer.hide();
			Audio.stop();
			ScoreBoard.hide();
			System.out.println("GAME IS PAUSED!");
			LoopGame.gamePause.show(Main.stage);
		}

	    if (input.contains("LEFT") && blackPanther.getRealX() > 0 ) {
			// x 70
	    	if(!blackPanther.isLocked()) {
	    		blackPanther.addVelocity(-200,0);
	    	}
            blackPanther.setFace("LEFT");
            blackPanther.setActionState(0);
        }
	    if (input.contains("RIGHT") && blackPanther.getRealX() < 1230 - blackPanther.getRealWidth()) {
	    	if(!blackPanther.isLocked()) {
	    		blackPanther.addVelocity(200,0);
	    	}            blackPanther.setFace("RIGHT");
            blackPanther.setActionState(0);
        }
	    if (input.contains("UP") && blackPanther.getRealY() > 210) {
        	// y 50
	    	if(!blackPanther.isLocked()) {
	    		blackPanther.addVelocity(0,-200);
	    	}        
	    	blackPanther.setFace(blackPanther.getFace());
            blackPanther.setActionState(0);

        }
		else if (input.contains("DOWN") && blackPanther.getRealY() < 800-blackPanther.getRealHeight()) {
			if(!blackPanther.isLocked()) {
	    		blackPanther.addVelocity(0,200);
	    	}                  
			blackPanther.setFace(blackPanther.getFace());
            blackPanther.setActionState(0);
		}
        if(input.contains(ATTACK_KEY) && blackPanther.isCanMovePosition() == true && StatusBar.attackIsReady()) {
			Audio.attackSound();
			blackPanther.attackEnemy(1);

        	Thread t = new Thread(()->{
    			try {
    				//setImageList
    	        	blackPanther.setAttackable(true);
    	        	//setImage
    	        	blackPanther.setFace(blackPanther.getFace());
    	        	//movePosition
    	        	blackPanther.nextPosition(blackPanther.getFace());
    	            blackPanther.setCanMovePosition(false);
    	            Thread.sleep(BlackPanther.ATTACK_COOLDOWN_VALUE);
    	            blackPanther.switchToWalk();
    	            blackPanther.setCanMovePosition(true);
    	            blackPanther.setActionState(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		});
    		t.start();
        }
	}
	
	public static int getKeyCombination() {
		if(directionInput.size() == 1) {
			if(input.contains("LEFT")) {
				return 1;
			} else if(directionInput.contains("RIGHT")) {
				return 2;

			} else if(directionInput.contains("UP")) {
				return 3;

			} else if(directionInput.contains("DOWN")){
				return 4;

			}
		} else if(directionInput.size() == 2) {
			if(directionInput.contains("LEFT") && directionInput.contains("RIGHT")) {
				return 0;

			} else if(directionInput.contains("LEFT") && directionInput.contains("UP")) {
				return 13;

			} else if(directionInput.contains("LEFT") && directionInput.contains("DOWN")) {
				return 14;

			} if(directionInput.contains("RIGHT") && directionInput.contains("UP")) {
				return 23;

			} else if(directionInput.contains("RIGHT") && directionInput.contains("DOWN")) {
				return 24;

			} else if(directionInput.contains("UP") && directionInput.contains("DOWN")) {
				return 0;

			}
			
		}else if(directionInput.size() == 3) {
			if(directionInput.contains("LEFT") && directionInput.contains("RIGHT") && directionInput.contains("UP")) {
				return 3;

			} else if(directionInput.contains("LEFT")&& directionInput.contains("RIGHT") && directionInput.contains("DOWN")) {
				return 4;

			} else if(directionInput.contains("RIGHT") && directionInput.contains("UP") && directionInput.contains("DOWN") ) {
				return 2;

			} else {
				return 1;
			}
				
		} else {
			
			return 0;
		}
		return 0;
	}
	
	
	
	
	
	
}
