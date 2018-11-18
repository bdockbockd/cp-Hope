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
    	if(this.getFace().equals("LEFT")) {
    		this.setImage((this.getImageL())[this.getPositionL()]);
    	} else {
    		this.setImage((this.getImageR())[this.getPositionR()]);
    	}
    }
    
    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY());
    }
    
    public String getName() {
    	return BlackTiger.name;
    }
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(this.getPositionX(),this.getPositionY(),this.getWidth()-100,this.getHeight()-100);
    }

}
