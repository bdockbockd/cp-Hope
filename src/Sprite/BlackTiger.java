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
    	return (this.getPositionX()<0) ? 0 : this.getPositionX()+71;
    	//73
    }
  
    public double getRealY() {
    	return (this.getPositionY()<0) ? 0 : this.getPositionY()-151.1;
    	//150
    }
    
    public Rectangle2D createBoundaryLeft() {
		return new Rectangle2D(this.getRealX()-53, this.getRealY()-51, 147, 188);
    	
    }
    
    public Rectangle2D createBoundaryRight() {
    	return new Rectangle2D(this.getRealX()+this.getRealWidth()+53-147,this.getRealY()-51, 147, 188);
    }
    
    
}
