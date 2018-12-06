package Sprite;

import java.util.ArrayList;

import Enemy.BadHuman;
import application.Images;
import application.Audio;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class TigerSprite extends Sprite{

	private boolean isMove;
	protected boolean attackable;
	protected int timesBasicAttack;
	protected boolean canMovePosition = true;

	private final double maxHealth = 1000;
	private double health;
	private double damage;
	private double armor;
	private int actionState = 0;
	private int status; // 1 = normalBP, 2 = superBP, 3 = enragedBP
	protected int spinPosition;
	protected int jumpPosition;
	protected boolean speedFix = false;

    public TigerSprite(Image image, Image[] imageList, Image[] imageL, Image[] imageR)
    {
       super(image, imageL, imageR);
       this.setImageList(imageList);
       this.setVelocityX(0);
       this.setPositionX(0);
       this.setVelocityY(0);
       this.setPositionY(0);
       
       this.health = maxHealth;
		this.damage = 100;
		this.armor = 10;
		this.status = 1;
    }
    
    public void setMove(boolean tf) { 
    	this.isMove = tf;
    }
    
    public boolean getMove() {
    	return this.isMove;
    }

    // add more type of setPosition 
    public void setPosition(double x, double y)
    {
        this.setPositionX(x);
        this.setPositionY(y-150);
    }
    
    public void setVelocity(double x, double y)
    {
        this.setVelocityX(x);
        this.setVelocityY(y);
    }

    public void addVelocity(double x, double y)
    {
        this.setVelocity(x+ this.getVelocityX(), y + this.getVelocityY());
        this.setMove(true);
    }

    public void update(double time)
    {
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
    }
    

    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY() );
    }

    
    public String toString()
    {
        return " Position: [" + this.getPositionX() + "," + this.getPositionY() + "]" 
        + " Velocity: [" + this.getVelocityX() + "," + this.getVelocityY() + "]";
    }


	public boolean isAttackable() {
		return attackable;
	}
	


	public int getTimesBasicAttack() {
		return timesBasicAttack;
	}

	public void setTimesBasicAttack(int timesBasicAttack) {
		this.timesBasicAttack = timesBasicAttack;
	}
	

	public boolean isCanMovePosition() {
		return canMovePosition;
	}

	public void setCanMovePosition(boolean canMovePosition) {
		this.canMovePosition = canMovePosition;
	}

	public void setAttackable(boolean attackable) {
		this.setActionState(1);
		this.attackable = attackable;
		this.setTimesBasicAttack((this.getTimesBasicAttack()+1)%3);
		if(attackable == true) {
			this.setImageL(Images.blackTigerBasicAttackL);
			this.setImageR(Images.blackTigerBasicAttackR);
		} else {
			this.setImageL(Images.blackTigerMotionL);
			this.setImageR(Images.blackTigerMotionR);
		}
	}  
	
	@Override
	public void setFace(String face) {
		if (!(face.equals(this.getFace()))) {
			this.face = face;
			this.setPositionR(0);
			this.setPositionL(0);
			this.setAttackable(false);
			return;
		}
		if(face == "LEFT") {
			if(this.getActionState() == 1) {
				this.setSkillPositionL((timesBasicAttack+1)%3);
			} else if(this.getActionState() == 2){
				this.setSpinPosition(0);
			} else if(this.getActionState() == 3){
				
				
				
				
				this.setJumpPosition(0);
			} else {
				this.setPositionL((this.getPositionL()+1)%3);
			}
		} else {
			if(this.getActionState() == 1) {
				this.setSkillPositionR((timesBasicAttack+1)%3);
			} else if(this.getActionState() == 2){
				this.setSpinPosition(0);
			} else if(this.getActionState() == 3){
				this.setJumpPosition(0);
			} else {
				this.setPositionR((this.getPositionR()+1)%3);
			}
		}
	}
	
	// use When want to immediately change
	public void switchToWalk() {
		this.setActionState(0);
		this.setSpeedFix(false);
		this.setAttackable(false);
		this.setImageL(Images.blackTigerMotionL);
		this.setImageR(Images.blackTigerMotionR);
		if(this.getFace() == "LEFT") {
			this.setImage((this.getImageL())[(this.getPositionL()+1)%3]);
		} else {
			this.setImage((this.getImageR())[(this.getPositionR()+1)%3]);
		}
	}
	
	public void setFace(String face, int duration) {

		if (!(face.equals(this.getFace()))) {
			this.face = face;
			this.setPositionR(0);
			this.setPositionL(0);
			return;
		}
		if(face == "LEFT") {
			if(this.isAttackable()) {
				this.setSkillPositionL((timesBasicAttack+1)%3);
			} else {
				this.setPositionL((this.getPositionL()+1)%3);
			}
		} else {
			if(this.isAttackable()) {
				this.setSkillPositionR((timesBasicAttack+1)%3);
			} else {
				this.setPositionR((this.getPositionR()+1)%3);
			}
		}
	}
	
	//getter & setter
	public double getMaxHealth() {
		return this.maxHealth;
	}
	public double getHealth() {
		return this.health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public double getDamage() {
		return this.damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	public double getArmor() {
		return this.armor;
	}
	public void setArmor(double armor) {
		this.armor = armor;
	}
	public int getStatus()
	{
		return this.status;
	}
	public void setStatus(int status)
	{
		this.status = status;
		if(status == 1)
		{
			setDamage(100);
			setArmor(10);
		}
		if(status == 2)
		{
			setDamage(300);
			setArmor(20);
		}
		if(status == 3)
		{
			setDamage(150);
			setArmor(15);
		}
	}
	
	public void heal(double heal)
	{
		setHealth(getHealth()+heal);
		if(getHealth()>getMaxHealth())
		{
			setHealth(getMaxHealth());
		}
	}
	
	
	public int getSpinPosition() {
		return spinPosition;
	}

	public void setSpinPosition(int spinPosition) {
		this.spinPosition = spinPosition;
	}

	public int getJumpPosition() {
		return jumpPosition;
	}

	public void setJumpPosition(int jumpPosition) {
		this.jumpPosition = jumpPosition;
	}

	public int getActionState() {
		return actionState;
	}

	public void setActionState(int actionState) {
		this.actionState = actionState;
	}

	public void takeDamage(double damage) {
		this.health -= damage;
		if(this.health < 0) {
			this.health = 0;
		}
	}
	public boolean isDead()
	{
		return getHealth()<=0;
	}

	public boolean isSpeedFix() {
		return speedFix;
	}

	public void setSpeedFix(boolean speedFix) {
		this.speedFix = speedFix;
	}
	

	

}
