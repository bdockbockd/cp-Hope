package Sprite;

import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BlackTiger extends TigerSprite {
	
	private static final String name = "blackTiger";

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
    	} else {
    		if(this.getFace().equals("LEFT")) {
    			this.setImage(this.getImageL()[this.getSkillPositionL()]);
    		} else {
    			this.setImage((this.getImageR())[this.getSkillPositionR()]);
    		}
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
//    	System.out.println("chilld");
        return new Rectangle2D(this.getPositionX(),this.getPositionY(),146,73);
        
    }
    
    @Override
    public void printBoundary() {
    	System.out.println( " Position: [" + this.getRealX() + "," + this.getRealY() + "]" 
    	        + " Width: [" + 160 + "," + 70 + "]");
    }
    public double getRealX() {
    	return this.getPositionX()+170;
    }
  
    public double getRealY() {
    	return this.getPositionY()+150;
    }
    
    
    
    
}
