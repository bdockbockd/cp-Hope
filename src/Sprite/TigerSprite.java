package Sprite;

import java.util.ArrayList;

import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class TigerSprite extends Sprite{
	
	public int tigerPosition = 0;
	private boolean isMove;


    public TigerSprite(Image image)
    {
       super(image);
       this.setVelocityX(0);
       this.setPositionX(0);
       this.setVelocityY(0);
       this.setPositionY(0);
    }
    
    
//    public static tigerSprite randomMoney() {
//    	tigerSprite moneybag = new tigerSprite();
//        moneybag.setImage("moneybag.png");
//        double px = 350 * Math.random() + 50;
//        double py = 350 * Math.random() + 50;          
//        moneybag.setPosition(px,py);
//        return moneybag;
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

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(this.getPositionX(),this.getPositionY(),this.getWidth(),this.getHeight());
    }

//    public boolean intersects(Sprite s)
//    {
//        return s.getBoundary().intersects( this.getBoundary() );
//    }
    
    public String toString()
    {
        return " Position: [" + this.getPositionX() + "," + this.getPositionY() + "]" 
        + " Velocity: [" + this.getVelocityX() + "," + this.getVelocityY() + "]";
    }  

}
