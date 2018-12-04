package Enemy;

import Sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HumanSprite extends Sprite {
	
	public int humanPosition = 0;
	private boolean isMove;
	private final double maxHealth = 500;
	private double health;
	private double damage;
	private boolean isDead = false;
	

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
	   this.damage = 10;
    }
    
//    Sprite briefcase = new Sprite();
//    briefcase.setImage("briefcase.png");
//    briefcase.setPosition(200, 0);

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

    public void update(double time)
    {
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        if(this.positionX < 100) {
        	this.setPositionX(100);
        }
        if(this.positionX > 1270){
        	this.setPositionX(1270);
        }
        if(this.positionY < 312) {
        	this.setPositionY(312);
        }
        if(this.positionY > 720) {
        	this.setPositionY(720);
        }
        
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
		}
		this.health = health;
	}
	public double getDamage() {
		return this.damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}



}
