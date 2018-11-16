package Sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite extends Rectangle {
	
	private Image image;
  
    private double velocityX, velocityY;
    
    public Sprite(Image image) {
    	super(0,0,image.getWidth(),image.getHeight());
    	this.setImage(image);
    }

    public double getVelocityX() {
    	return this.velocityX;
    }
    public void setVelocityX(double velocityX) {
    	this.velocityX = velocityX;
    }
    public double getVelocityY() {
    	return this.velocityY;
    }
    public void setVelocityY(double velocityY) {
    	this.velocityY = velocityY;
    }
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(this.getPositionX(),this.getPositionY(),this.getWidth(),this.getHeight());
    }

    public String printBoundary() {
    	return " Position: [" + this.getPositionX() + "," + this.getPositionY() + "]" 
    	        + " Width: [" + this.getWidth() + "," + this.getHeight() + "]";
    }
    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects(this.getBoundary());
    }
	
	public void setImage(Image i) {
		this.image = i;
	}
	public Image getImage() {
		 return this.image;
	}
	public abstract void update(double time);
	public abstract void render(GraphicsContext gc);
	
}
