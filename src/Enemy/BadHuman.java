package Enemy;

import Sprite.BlackTiger;
import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BadHuman extends HumanSprite  {

	private String name;
	public BadHuman() {
		super((Images.humanMotionR)[0], Images.humanMotionR, Images.humanMotionL, Images.humanMotionR);
		// TODO Auto-generated constructor stub
	}

    public void nextPosition(String direction) {
    	if(this.getFace().equals("LEFT")) {
    		this.setImage((this.getImageL())[this.getPositionL()]);
    	} else {
    		this.setImage((this.getImageR())[this.getPositionR()]);
    	}
    }
    
    public static BadHuman generateRandom() {
    	Sprite BadHuman = new BadHuman();
    }
    //  {
//      Sprite moneybag = new Sprite();
//      moneybag.setImage("moneybag.png");
//      double px = 350 * Math.random() + 50;
//      double py = 350 * Math.random() + 50;          
//      moneybag.setPosition(px,py);
//      moneybagList.add( moneybag );
//  }
    
    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX()-100, this.getPositionY()-100 );
    }
    
    public String getName() {
    	return this.name;
    }
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(this.getPositionX(),this.getPositionY(),this.getWidth()-100,this.getHeight()-100);
    }

}
}
