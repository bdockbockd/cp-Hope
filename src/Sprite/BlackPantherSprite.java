package Sprite;

import java.util.List;

import Constant.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class BlackPantherSprite extends Sprite /*implements HasSkill*/{

	private static final String name = "BlackPantherSprite";
	private boolean isMove;
	protected boolean attackable;
	protected int timesBasicAttack;
	protected boolean canMovePosition = true;

	private final double maxHealth = 1000;
	private double health;
	private double damage;
	private double armor;
	protected int actionState = 0;
	protected int spinPosition;
	protected int jumpPosition;
	protected boolean speedFix;

    public BlackPantherSprite(Image image, Image[] imageList, Image[] imageL, Image[] imageR)
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
		this.speedFix = false;
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

	//change to IMAGELIST
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
	
	
	//SET Position
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
	
	// use When want to immediately change change Image List and Image
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
	
	  public double getRealWidth() {
	    	return 171.238;
	    }
	    public double getRealHeight() {
	    	return 90.775;
	    }
	    public double getRealX() {
	    	return this.getPositionX()+75;
	    	//73
	    }
	  
	    public double getRealY() {
	    	return this.getPositionY()+60;
	    	//150
	    }
	    
	    public Rectangle2D createBoundaryLeft() {
			return new Rectangle2D(this.getRealX()-53, this.getRealY()-51, 147, 188);
	    	
	    }
	    
	    public Rectangle2D createBoundaryRight() {
	    	return new Rectangle2D(this.getRealX()+this.getRealWidth()+53-147,this.getRealY()-51, 147, 188);
	    }
	    @Override
	    public Rectangle2D getBoundary()
	    {
	        return new Rectangle2D(this.getRealX(),this.getRealY(),this.getRealWidth(),this.getRealHeight());    
	    }
	    
	    @Override
	    public void printBoundary() {
	    	System.out.println( "Name:"+this.getName()+" Position: [" + this.getRealX() + "," + this.getRealY() + "]" 
	    	        + " Width: [" + this.getRealWidth() + "," + this.getRealHeight() + "]");
	    }
	
	
	//getter & setter
	public double getMaxHealth() {
		return this.maxHealth;
	}
	public double getHealth() {
		return this.health;
	}
	public void setHealth(double health) {
		if(health<0) health = 0;
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
		if(this.getActionState() == actionState) return;
		this.actionState = actionState;
	}

	public boolean isSpeedFix() {
		return speedFix;
	}

	public void setSpeedFix(boolean speedFix) {
		this.speedFix = speedFix;
	}

	public String getName() {
		return name;
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
	

	

}
