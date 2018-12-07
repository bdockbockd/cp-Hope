package Sprite;

import java.util.ArrayList;
import java.util.List;

import Constant.Audio;
import Constant.Images;
import Enemy.BadHuman;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BlackPanther extends BlackPantherSprite implements HasStatus {

	private static final String name = "BlackPantherX";
	public static boolean spinAttackDetected = false;
	public static boolean jumpAttackDetected = false;
	public static long ATTACKCOOLDOWN = 200; //sec
	public static long JUMPCOOLDOWN = 500; //sec
	public static long SPINCOOLDOWN = 5*1000; //sec
	
//	private int status; // 0 = normalBP, 1 = superBP, 2 = enragedB
	public static int STATUS = 0;
	public static boolean ISSUPER = false;
//	private static Image[][] STATUSTIGER = BlackPanther.IMAGESTAGEDEFAULT;
	
	public BlackPanther() {
		super((Images.blackTigerMotionR)[0], Images.blackTigerMotionR, Images.blackTigerMotionL, Images.blackTigerMotionR);
		checkStatus();
	}

	public void switchToWalk() {
		this.setActionState(0);
	}
	@Override
	public void setAttackable(boolean attackable) {
		this.setActionState(1);
		this.attackable = attackable;
		this.setTimesBasicAttack((this.getTimesBasicAttack()+1)%3);

	}  
	
    public void nextPosition(String direction) {
    	//Motion
    	if(this.getActionState() == 0) {
    		if(this.getFace().equals("LEFT")) {
    			this.setImage((this.getStageTiger().get(0))[this.getPositionL()]);
    		} else {
    			this.setImage((this.getStageTiger().get(1))[this.getPositionR()]);
    		}
    		//
    	} else if(this.getActionState() == 2){
    		if(this.getFace().equals("LEFT")) {
        		this.setImage(((this.getStageTiger()).get(6))[this.getSpinPosition()]); 
    		} else {
        		this.setImage(((this.getStageTiger()).get(7))[this.getSpinPosition()]);
    		}
    	}else if(this.getActionState() == 3){    
    		if(this.getFace().equals("LEFT")) {
        		this.setImage(((this.getStageTiger()).get(4))[this.getJumpPosition()]);
    		} else {
        		this.setImage(((this.getStageTiger()).get(5))[this.getJumpPosition()]);		
    		}
    	}
		else {
			// attack
    		if(this.getFace().equals("LEFT")) {
    			this.setImage(this.getStageTiger().get(2)[this.getSkillPositionL()]);
    		} else {
    			this.setImage(this.getStageTiger().get(3)[this.getSkillPositionR()]);
    		}
    	}
    }
    
    public void attackEnemy() {
		Audio.HITDETECTED = false;
		if(this.getFace() == "LEFT") {
			BadHuman enemy;
			for(int i=0;i<BadHuman.getbadList().size();i++) {
				enemy = BadHuman.getbadList().get(i);
				if(enemy.getBoundary().intersects(this.createBoundaryLeft())) {
					if(!(enemy.isDead())) {
						
					}
					enemy.setHealth(enemy.getHealth()-this.getDamage());
						
					Audio.HITDETECTED = true;	
				}
			}
		} else {
			BadHuman enemy;
			for(int i=0;i<BadHuman.getbadList().size();i++) {
				enemy = BadHuman.getbadList().get(i);
				if(enemy.getBoundary().intersects(this.createBoundaryRight())) {				
					enemy.setHealth(enemy.getHealth()-this.getDamage());

					Audio.HITDETECTED = true;
				}
			}
		}
		if(Audio.HITDETECTED == true) {
			Audio.playGetHit(0);						
		}
	}
    
	public void playJump(String direction) {
		if(direction.equals("LEFT")) {
			Thread delay = new Thread(()->{
				try {
					this.setActionState(3);
					Thread.sleep(100);
					this.setVelocity(-1200,-100);
					Thread.sleep(50);
					this.setVelocity(-1200, 100);
					Thread.sleep(50);
					this.setVelocity(-1200,200);
					Thread.sleep(100);
					this.setSpeedFix(false);
					this.switchToWalk();
					Thread.sleep(10);
					this.setSpeedFix(false);
//					tiger1.setVelocityX(0);
					Thread.sleep(JUMPCOOLDOWN);
					BlackPanther.jumpAttackDetected = false;

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			});
			delay.start();
		} else {
			Thread delay = new Thread(()->{
				try {
					this.setActionState(3);
					Thread.sleep(50);
					this.setVelocity(1200,-100);
					Thread.sleep(70);
					this.setVelocity(1200, 100);
					Thread.sleep(70);
					this.setVelocity(1200,200);
					Thread.sleep(50);
					this.switchToWalk();
					this.setSpeedFix(false);
//					tiger1.setVelocityX(0);
					Thread.sleep(JUMPCOOLDOWN);
					BlackPanther.jumpAttackDetected = false;

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			});
			delay.start();
		}
	}

	// STATUS PART
	public int getStatus()
	{
		if(ISSUPER) {
			return 1;
		}
		else if(getHealth()/getMaxHealth() <= 0.3) {
			return 2;
		}
		else{
			return 0;
		}
	}
	public void setStatus()
	{
		BlackPanther.STATUS = getStatus();
		if(BlackPanther.STATUS == 0)
		{
			setDamage(100);
			setArmor(10);
		}
		else if(BlackPanther.STATUS == 1)
		{
			setDamage(300);
			setArmor(20);
		}
		else if(BlackPanther.STATUS == 2)
		{
			setDamage(150);
			setArmor(15);
		}
	}
	
	public void checkStatus() {
		this.setStatus();
	}
	
	public ArrayList<Image[]> getStageTiger() {
		return (Images.STAGETIGER).get(this.getStatus());
	}

	
}
