package Item;

import Controller.LoopGame;
import Enemy.GunMan;
import Sprite.BlackPanther;
import Sprite.Moveable;
import javafx.scene.image.Image;

public class Bullet extends Item implements Moveable {
    private double velocityX = 0, velocityY =0;

	public Bullet(double x, double y, Image image, Image transparentImage, GunMan enemy) {
		super(x, y, image, transparentImage, true, enemy);
		
		Thread appear = new Thread(()->{
			itemList.add(this);
			while(true) {
				this.setVelocityX(100);
				if(this.intersect(LoopGame.blackPanther)) {
//					LoopGame.blackPanther
					enemy.attack(LoopGame.blackPanther);
					break;
				} 
				if(this.getPositionX() > 1250 || this.getPositionX() < 0) {
					break;
				}
			}
			itemList.remove(this);
		});
		appear.start();
	}

	//getter and setter
	@Override
	public void itemUse(BlackPanther blackPanther) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getVelocityX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setVelocityX(double velocityX) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getVelocityY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setVelocityY(double velocityY) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateBullet(double time){
	        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
	        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
	}

}
