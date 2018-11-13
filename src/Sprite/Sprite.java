package Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
	
	private Image image;
    private double positionX;
    private double positionY;    
    private double velocityX, velocityY;

    private double width;
    private double height;
  
    public Sprite(Image image) {
    	this.setImage(image);
    }

	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
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
    
    public boolean collideWidth(Sprite object) {
    	double thisX = this.getPositionX();
    	double thisY = this.getPositionY();
    	
    	double objectX = object.getPositionX();
    	double objectY = object.getPositionY();
    	
    	double absX = Math.abs(thisX-objectX);
    	double absY = Math.abs(thisY-objectY);
    	return (absX <10 || absY <10) ? true : false;
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
