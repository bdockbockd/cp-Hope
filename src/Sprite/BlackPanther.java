package Sprite;

import java.util.ArrayList;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Controller.LoopGame;
import Enemy.BadHuman;
import Enemy.HumanSprite;
import javafx.scene.image.Image;

public class BlackPanther extends BlackPantherSprite implements HasStatus{
	
	private static final String name = "BlackPanther";
	public static boolean spinAttackDetected = false;
	public static boolean jumpAttackDetected = false;
	public static final long ATTACK_COOLDOWN_CONSTANT = 400; //sec
	public static long ATTACK_COOLDOWN_VALUE = 200;
	public static long JUMP_COOLDOWN = 1*1000; //sec
	public static long SPIN_COOLDOWN = 5*1000; //sec
	
//	private int status; // 0 = normalBP, 1 = superBP, 2 = enragedB
	public static int STATUS = 0;
	public static boolean ISSUPER = false;
	private boolean isGod;
//	private static Image[][] STATUSTIGER = BlackPanther.IMAGESTAGEDEFAULT;
	
	public BlackPanther() {
		super((Images.blackTigerMotionR)[0], Images.blackTigerMotionR, Images.blackTigerMotionL, Images.blackTigerMotionR);
		checkStatus();
	}
	public void takeDamage(double damage) {
		if(!isGod) {
			this.setHealth(this.getHealth()-damage);
		}
	}
	public boolean isDead()
	{
		return (getHealth()<=0 && !isGod);
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
    
    public void attackEnemy(int actionState) {
		Audio.HITDETECTED = false;
		LoopGame.botHit = false;
		if(this.getFace() == "LEFT") {
			HumanSprite enemy;
			for(int i=0;i<EnemyGen.getbadList().size();i++) {
				enemy = EnemyGen.getbadList().get(i);
				if(enemy.getBoundary().intersects(this.createBoundaryLeft())) {
					enemy.setKnockBack(true);
					LoopGame.botHit = true;
					if(!(enemy.isDead())) {
						 enemy.knockBack(this.getFace(), actionState, (enemy.getPositionY() > this.getPositionY()));
					}
					enemy.setHealth(enemy.getHealth()-this.getDamage());
						
					Audio.HITDETECTED = true;	
				}
			}
		} else {
			HumanSprite enemy;
			for(int i=0;i<EnemyGen.getbadList().size();i++) {
				enemy = EnemyGen.getbadList().get(i);
				if(enemy.getBoundary().intersects(this.createBoundaryRight())) {				
					enemy.setHealth(enemy.getHealth()-this.getDamage());
					LoopGame.botHit = true;
					if(!(enemy.isDead())) {
						 enemy.knockBack(this.getFace(), actionState, (enemy.getPositionY() > this.getPositionY()));
					}
					Audio.HITDETECTED = true;
				}
			}
		}
		if(Audio.HITDETECTED == true) {
			Audio.playGetHit(0);						
		}
	}
    
	public void playJump(String direction) {
		this.setFace(direction);
		this.nextPosition(direction);
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
					this.setSkillOn(false);
					this.switchToWalk();
					Thread.sleep(10);
					this.setSkillOn(false);
//					blackPanther1.setVelocityX(0);
					Thread.sleep(BlackPanther.JUMP_COOLDOWN);
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
					this.setSkillOn(false);
//					blackPanther1.setVelocityX(0);
					Thread.sleep(BlackPanther.JUMP_COOLDOWN);
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
			BlackPanther.ATTACK_COOLDOWN_VALUE = BlackPanther.ATTACK_COOLDOWN_CONSTANT;
			setDamage(100);
			setArmor(10);
		}
		else if(BlackPanther.STATUS == 1)
		{
			BlackPanther.ATTACK_COOLDOWN_VALUE = BlackPanther.ATTACK_COOLDOWN_CONSTANT/2;
			setDamage(300);
			setArmor(20);
		}
		else if(BlackPanther.STATUS == 2)
		{
			BlackPanther.ATTACK_COOLDOWN_VALUE = BlackPanther.ATTACK_COOLDOWN_CONSTANT;
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
	public boolean isGod() {
		return isGod;
	}
	
	public void enableGodMode() {
		isGod = true;
	}
	
	public void disableGodMode() {
		isGod = false;
	}
	public void reset() {
		this.setActionState(0);
		BlackPanther.ISSUPER = false;
	}
	
}
