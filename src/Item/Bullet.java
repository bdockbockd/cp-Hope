package Item;

import Controller.LoopGame;
import Enemy.GunMan;
import Sprite.BlackPanther;
import Sprite.Moveable;
import javafx.scene.image.Image;

public class Bullet extends Item implements Moveable {
    private double velocityX = 0, velocityY =0;
    private static final int BULLETDAMAGE = 40;

	public Bullet(double x, double y, Image image) {
		super(x,y,image);
		if(x<600) {
			this.setVelocityX(500);
		} else {
			this.setVelocityX(-500);
		}
		itemList.add(this);		
	}

	//getter and setter
	@Override
	public void itemUse(BlackPanther blackPanther) {
		
		blackPanther.setHealth(blackPanther.getHealth()-BULLETDAMAGE);

	}
	public double getVelocityX() {
		return this.velocityX;
	}
	
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	public double getVelocityY() {
		return 0;
	}
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public void updateBullet(double time){
	        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
	        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
	}

}
