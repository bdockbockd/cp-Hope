package Sprite;

import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BlackTiger extends TigerSprite {
	
	private static final String name = "blackTiger";
	private static Image[] tigerImageL = Images.blackTigerMotionL;
	private static Image[] tigerImageR = Images.blackTigerMotionR;

	public BlackTiger() {
		super(tigerImage[0]);
	}
	
    public Image nextPosition() {
    	this.tigerPosition = (this.tigerPosition+1)%tigerImage.length;
    	return (tigerImage)[tigerPosition];
    }
    
    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX()-100, this.getPositionY()-100 );
    }
    
    public String getName() {
    	return BlackTiger.name;
    }
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(this.getPositionX(),this.getPositionY(),this.getWidth()-100,this.getHeight()-100);
    }

}
