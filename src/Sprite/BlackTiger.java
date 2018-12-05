package Sprite;

import Controller.Main;
import Enemy.BadHuman;
import application.Images;
import application.Audio;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BlackTiger extends TigerSprite {
	
	private static final String name = "blackTiger";
	// list of list >> stage
	public static boolean spinAttackDetected = false;
	public static boolean jumpAttackDetected = false;
	
	public BlackTiger() {
		super((Images.blackTigerMotionR)[0], Images.blackTigerMotionR, Images.blackTigerMotionL, Images.blackTigerMotionR);
	}
	
    public void nextPosition(String direction) {
    	if(!this.isAttackable()) {
    		if(this.getFace().equals("LEFT")) {
    			this.setImage((this.getImageL())[this.getPositionL()]);
    		} else {
    			this.setImage((this.getImageR())[this.getPositionR()]);
    		}
    	} else if(BlackTiger.spinAttackDetected){
    		if(this.getFace().equals("LEFT")) {
        		this.setImage((Images.spinAttackL)[this.getSpinPosition()]);
    		} else {
        		this.setImage((Images.spinAttackR)[this.getSpinPosition()]);
    		}
    	}else if(BlackTiger.jumpAttackDetected){    
    		if(this.getFace().equals("LEFT")) {
        		this.setImage((Images.jumpAttackL)[this.getJumpPosition()]);
    		} else {
        		this.setImage((Images.jumpAttackR)[this.getJumpPosition()]);		
    		}
    	}
		else {

    		if(this.getFace().equals("LEFT")) {
    			this.setImage(this.getImageL()[this.getSkillPositionL()]);
    		} else {
    			this.setImage((this.getImageR())[this.getSkillPositionR()]);
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
					
					enemy.setHealth(enemy.getHealth()-this.getDamage());
//					if(enemy.isDead() == true) {
//
//						Enemy.BadHuman.getbadList().remove(i);
//					}
					Audio.HITDETECTED = true;	

				}

			}
		} else {
			BadHuman enemy;
			for(int i=0;i<BadHuman.getbadList().size();i++) {
				enemy = BadHuman.getbadList().get(i);
				if(enemy.getBoundary().intersects(this.createBoundaryRight())) {
					
					enemy.setHealth(enemy.getHealth()-this.getDamage());
//					if(enemy.isDead() == true) {
////						Enemy.BadHuman.getbadList().get(i)
//						Main.gc.drawImage(Images.enemyTomb, enemy.getPositionX(), enemy.getPositionY());
//						Enemy.BadHuman.getbadList().remove(i);
//					}
					Audio.HITDETECTED = true;

				}
			}
		}
		if(Audio.HITDETECTED == true) {
			Audio.playGetHit(0);						
		}
	}
    
    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY());
    }
    
    public String getName() {
    	return BlackTiger.name;
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
    
    
}
