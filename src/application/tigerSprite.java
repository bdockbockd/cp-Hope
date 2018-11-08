package application;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class tigerSprite {
	
    private Image image;
	private Image[] tigerImage = Images.bigTigerMotion;
	public int tigerPosition = 0;
	
	private int duration;
	private boolean isMove;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public tigerSprite()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
        this.setImage((Images.bigTigerMotion)[0]);
    }
    
    public void changeType() {
    	this.tigerImage = Images.blackTigerMotion;
    	this.setImage((Images.blackTigerMotion)[0]);
    }
    
    public static tigerSprite randomMoney() {
    	tigerSprite moneybag = new tigerSprite();
        moneybag.setImage("moneybag.png");
        double px = 350 * Math.random() + 50;
        double py = 350 * Math.random() + 50;          
        moneybag.setPosition(px,py);
        return moneybag;
    }
    
    public Image getImage() {
    	return this.image;
    }
    
    public Image nextPosition() {
    	this.tigerPosition = (this.tigerPosition+1)%this.tigerImage.length;
    	return (this.tigerImage)[tigerPosition];
    }
//    public void getCollided() {
//    	this.setVelocity(0, 0);
//    }
    
//    public ArrayList<Sprite> getSpriteList() {
//    	return this.spriteList;
//    }
//    
//    public void addList(Sprite e) {
//    	this.spriteList.add(e);
//    }
    
    public int getDuration() {
    	return this.duration;
    }

    public void setDuration(int n) {
    	this.duration = n;
    }
    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

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

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }
    public double getPositionX() {
    	return positionX;
    }
    public double getPositionY() {
    	return positionY;
    }
    
    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
        this.setMove(true);
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
        this.setImage(this.nextPosition());
    }
    public void update(double time, int duration)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
        if(duration==0) {
        	this.setImage(this.nextPosition());
        }
    	this.setDuration(duration-1);

    }
    

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

//    public boolean intersects(Sprite s)
//    {
//        return s.getBoundary().intersects( this.getBoundary() );
//    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

}
