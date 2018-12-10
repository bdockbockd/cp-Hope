package Enemy;

import java.util.concurrent.CopyOnWriteArrayList;

import Constant.Images;
import Sprite.BlackPanther;
import Sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class HumanSprite extends Sprite {
	
    private String name = "HumanSprite";
	public int humanPosition = 0;
	private boolean isMove;
	private final double maxHealth = 500;
	private double health;
	private final double damage = 10;
	private boolean isDead = false;
    private boolean isTomb = false;
    private boolean waitToHit = false;
    private boolean knockBack = false;



	protected boolean attackStage;

    public HumanSprite(Image image, Image[] imageList, Image[] imageL, Image[] imageR)
    {
       super(image, imageL, imageR);
       this.setImageList(imageList);
       this.setVelocityX(0);
       this.setPositionX(0);
       this.setVelocityY(0);
       this.setPositionY(0);
       
       this.health = maxHealth;
    }    
    public void update(double time)
    {
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        if(this.positionX < 0) {
//        	this.setPositionX(0);
        }
        if(this.positionX > 1250-this.getWidth()){
//        	this.setPositionX(1250-this.getWidth());
        }
        if(this.positionY < 210) {
        	this.setPositionY(210);
        }
        if(this.positionY > 800-this.getHeight()) {
        	this.setPositionY(800-this.getHeight());
        }
        
    }
    
	 public void attack(BlackPanther tiger) {
		// TODO Auto-generated method stub
		tiger.takeDamage(this.getDamage());
	}

    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY() );
    }
    
    // getter and setter
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
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
        this.setPositionY(y);
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
    
    public String toString()
    {
        return " Position: [" + this.getPositionX() + "," + this.getPositionY() + "]" 
        + " Velocity: [" + this.getVelocityX() + "," + this.getVelocityY() + "]";
    }  
    
	//getter & setter
	public double getMaxHealth() {
		return this.maxHealth;
	}
	public double getHealth() {
		return this.health;
	}
	public void setHealth(double health) {
		if(health <= 0) {
			this.setDead(true);
			this.setVelocity(0, 0);
		}
		this.health = health;
	}
	public double getDamage() {
		return this.damage;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isWaitToHit() {
		return waitToHit;
	}
	public void setWaitToHit(boolean waitToHit) { //change of this by Ou
		if(this.waitToHit != waitToHit)
		{
			this.setFace(this.getFace());
	        this.nextPosition(this.getFace());
		}
		this.waitToHit = waitToHit;
	}
	
//	public void nextPosition(String direction) {
//	public void update(double time, BlackPanther tiger)
//    public static void checkAttackHuman(BlackPanther tiger) {
	public boolean isTomb() {
		return isTomb;
	}
	public void setTomb(boolean isTomb) {
		this.isTomb = isTomb;
	}
	
	public boolean isKnockBack() {
		return knockBack;
	}
	public void setKnockBack(boolean knockBack) {
		this.knockBack = knockBack;
	}
	
	public abstract void update(double time, BlackPanther tiger);
	public abstract void knockBack(String direction, int stateSkill, boolean isBotHigher);

}
