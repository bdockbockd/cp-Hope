package Controller;

import java.util.ArrayList;

import Constant.Audio;
import Sprite.BlackPanther;
import UI.GamePause;
import javafx.animation.AnimationTimer;

public class KeyControlBlackPanther {
	
    public static ArrayList<String> input;
    
    public static final String ATTACK_KEY = "Z";
    public static final String JUMP_KEY = "X";
    public static final String SPIN_KEY = "C";
    public static final String GODMODE_ON_KEY = "I";
    public static final String GODMODE_OFF_KEY = "O";
    public static final String GETSCORE_KEY = "P";


	public static void keyActionToSpeed(BlackPanther tiger, long current, AnimationTimer x) {
		
		if(input.contains(JUMP_KEY) && BlackPanther.jumpAttackDetected == false && StatusBar.pounceIsReady()) {
			tiger.setActionState(3);
			BlackPanther.jumpAttackDetected = true;
			tiger.setSkillOn(true);
			String direction = tiger.getFace();
			tiger.playJump(direction);
			Audio.pounceSound();
			tiger.attackEnemy(3);
			return;
			//set Speed Fix in here
		} else if(input.contains(SPIN_KEY) && BlackPanther.spinAttackDetected == false && StatusBar.spinIsReady()) {

			tiger.setActionState(2);
			tiger.attackEnemy(2);
			
			Audio.spinSound();
			tiger.setSkillOn(true);

			Thread delay = new Thread(()-> {
				BlackPanther.spinAttackDetected = true;
				tiger.setFace(tiger.getFace());
				tiger.nextPosition(tiger.getFace());	
				try {
					int loop = 6;
					while(loop!=0) {
						tiger.update(0.016);
						tiger.attackEnemy(2);
						
						tiger.setVelocity(-1000, 0);
						Thread.sleep(30);
						tiger.setFace(tiger.getFace());
						tiger.attackEnemy(2);
						tiger.setActionState(2);
						tiger.nextPosition(tiger.getFace());
						tiger.setVelocity(1000, 0);
						Thread.sleep(30);
						tiger.setFace("OPPOSITE");
						tiger.attackEnemy(2);

						tiger.setActionState(2);

						tiger.nextPosition(tiger.getFace());	
		
						tiger.setVelocity(-1000, 0);
						
						Thread.sleep(30);
						tiger.setFace(tiger.getFace());
						tiger.attackEnemy(2);
						tiger.setActionState(2);

						tiger.nextPosition(tiger.getFace());
						tiger.setVelocity(1000, 0);
						Thread.sleep(30);
						tiger.setFace("OPPOSITE");
						tiger.attackEnemy(2);

//						tiger.setActionState(2);
						tiger.nextPosition(tiger.getFace());	

		        		loop--;
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BlackPanther.spinAttackDetected = false;
				tiger.setSkillOn(false);
				tiger.switchToWalk();
			});
			delay.start();
		} 
		
		if(input.contains(GODMODE_ON_KEY)) {
			tiger.enableGodMode();
			System.out.println("enableGodMode!");
		}
		else if(input.contains(GODMODE_OFF_KEY)) {
			tiger.disableGodMode();
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
			ScoreBoard.hide();
			System.out.println("GAME IS PAUSED!");
			LoopGame.gamePause.show(Main.stage);
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
        if(input.contains(ATTACK_KEY) && tiger.isCanMovePosition() == true && StatusBar.attackIsReady()) {
			Audio.attackSound();
			tiger.attackEnemy(1);

        	Thread t = new Thread(()->{
    			try {
    				//setImageList
    	        	tiger.setAttackable(true);
    	        	//setImage
    	        	tiger.setFace(tiger.getFace());
    	        	//movePosition
    	        	tiger.nextPosition(tiger.getFace());
    	            tiger.setCanMovePosition(false);
    	            Thread.sleep(BlackPanther.ATTACK_COOLDOWN_VALUE);
    	            tiger.switchToWalk();
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
}
