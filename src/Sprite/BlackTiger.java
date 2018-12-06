package Sprite;

import Constant.Audio;
import Constant.Images;
import Controller.Main;
import Enemy.BadHuman;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BlackTiger extends BlackPantherSprite {
	
	private static final String name = "blackTiger";
	public static boolean spinAttackDetected = false;
	public static boolean jumpAttackDetected = false;
	
	public BlackTiger() {
		super((Images.blackTigerMotionR)[0], Images.blackTigerMotionR, Images.blackTigerMotionL, Images.blackTigerMotionR);
	}
	
    public void nextPosition(String direction) {
    	if(this.getActionState() == 0) {
    		if(this.getFace().equals("LEFT")) {
    			this.setImage((this.getImageL())[this.getPositionL()]);
    		} else {
    			this.setImage((this.getImageR())[this.getPositionR()]);
    		}
    	} else if(this.getActionState() == 2){
    		if(this.getFace().equals("LEFT")) {
        		this.setImage((Images.spinAttackL)[this.getSpinPosition()]); 
    		} else {
        		this.setImage((Images.spinAttackR)[this.getSpinPosition()]);
    		}
    	}else if(this.getActionState() == 3){    
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
    
	public void playJump() {
		if(this.getFace() == "LEFT") {
			this.setFace("LEFT");
			this.nextPosition(this.getFace());
			this.setSpeedFix(true);
			this.setImage((Images.jumpAttackL)[0]);
			Thread delay = new Thread(()->{
				try {
					this.setVelocity(-1200,-200);
					Thread.sleep(100);
					this.setVelocity(-1200,-100);
					Thread.sleep(50);
					this.setVelocity(-1200, 100);
					Thread.sleep(50);
					this.setVelocity(-1200,200);
					Thread.sleep(100);
					this.switchToWalk();
					Thread.sleep(10);
					this.setSpeedFix(false);
//					tiger1.setVelocityX(0);
//					Thread.sleep(2000);
					BlackTiger.jumpAttackDetected = false;

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			});
			delay.start();
		} else {
			this.nextPosition(this.getFace());
			this.setFace("RIGHT");
			this.setImage((Images.jumpAttackR)[0]);
			this.setSpeedFix(true);
			Thread delay = new Thread(()->{
				try {
					this.setVelocity(1200,-200);
					Thread.sleep(50);
					this.setVelocity(1200,-100);
					Thread.sleep(70);
					this.setVelocity(1200, 100);
					Thread.sleep(70);
					this.setVelocity(1200,200);
					Thread.sleep(50);
					this.switchToWalk();
					Thread.sleep(30);
					this.setSpeedFix(false);
//					tiger1.setVelocityX(0);
//					Thread.sleep(2000);
					BlackTiger.jumpAttackDetected = false;

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			});
			delay.start();
		}
	}
}
