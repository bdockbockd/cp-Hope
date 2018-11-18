package Enemy;

import Sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HumanSprite extends Sprite {
	
	public int humanPosition = 0;
	private boolean isMove;

	protected boolean attackStage;

    public HumanSprite(Image image, Image[] imageList, Image[] imageL, Image[] imageR)
    {
       super(image, imageL, imageR);
       this.setImageList(imageList);
       this.setVelocityX(0);
       this.setPositionX(0);
       this.setVelocityY(0);
       this.setPositionY(0);
    }
    
//    Sprite briefcase = new Sprite();
//    briefcase.setImage("briefcase.png");
//    briefcase.setPosition(200, 0);
//     
//    ArrayList<Sprite> moneybagList = new ArrayList<Sprite>();
     
//    for (int i = 0; i < 15; i++)
//    {
//        Sprite moneybag = new Sprite();
//        moneybag.setImage("moneybag.png");
//        double px = 350 * Math.random() + 50;
//        double py = 350 * Math.random() + 50;          
//        moneybag.setPosition(px,py);
//        moneybagList.add( moneybag );
//    }

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


}
