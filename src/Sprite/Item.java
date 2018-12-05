package Sprite;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Item extends Rectangle{
	private Image image;
	public Item(double x, double y,Image image) {
		super(x, y, image.getWidth(), image.getHeight());
		this.image = image;
	}
	public abstract void itemUse();
	public abstract void update(double time);
	public Image getImage() {
		return this.image;
	}
	public void render(GraphicsContext gc) {
		gc.drawImage(image, this.positionX, this.positionY);
	}
	
	
	/*
	public void update(double time)
    {
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        if(this.positionX < 100) {
        	this.setPositionX(100);
        }
        if(this.positionX > 1270){
        	this.setPositionX(1270);
        }
        if(this.positionY < 312) {
        	this.setPositionY(312);
        }
        if(this.positionY > 720) {
        	this.setPositionY(720);
        }
        
    }
    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY() );
    }
    */
}
