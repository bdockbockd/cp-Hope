package Enemy;

import Constant.Audio;
import Constant.Images;
import Controller.EnemyGen;
import Controller.LoopGame;
import Item.Bullet;
import Sprite.BlackPanther;
import UI.GamePause;
import javafx.scene.media.AudioClip;

public class GunMan extends BadHuman {
	private boolean isReadyToFire;
	
	public GunMan() {
		this.setImage((Images.GUNMANR)[0]);
		this.setImageL(Images.GUNMANL);
		this.setImageR(Images.GUNMANR);
		this.setImageList(Images.GUNMANL);
		this.isReadyToFire = true;
//		fire.start();
	}
	
	//update all time
	@Override
	public void update(double time) {
		if(this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			return;
		}
	        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
	        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
	        if(this.getPositionX() > 5 && this.getPositionX() < 500) {
	        	this.setPositionX(5);
	        } else if(this.getPositionX()< 1135 && this.getPositionX() > 800) {
	        	this.setPositionX(1135);
	        }
	        if(this.positionY < 180) {
	        	this.setPositionY(180);
	        }
	        if(this.positionY > 800-this.getHeight()-30) {
	        	this.setPositionY(800-this.getHeight()-30);
	        }
	    }
	
	//update every one time
	@Override
	public void update(double time, BlackPanther blackPanther) {
		if(this.isDead()) {
			this.setVelocity(0, 0);
			this.setImage(Images.enemyTomb);
			this.update(time);
			return;
		}
		if(this.isReadyToFire) {
			Thread delayToFire = new Thread(()->{
				try {
					this.isReadyToFire = false;
					Thread.sleep(1000);
					this.fireBullet();
					this.isReadyToFire = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			delayToFire.start();
		}
//		this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
        if( (this.getPositionY() > blackPanther.getPositionY())) {
        	if(this.getPositionX() < 100) {
        		this.setVelocityX(200);
        	} else if (this.getPositionX() > 1175) {
        		this.setVelocityX(-200);
        	}
        	this.setVelocityY(-200);
        } else {
        	this.setVelocityY(200);
        	if(this.getPositionX() < 100) {
        		this.setVelocityX(200);
        	} else if (this.getPositionX() > 1175) {
        		this.setVelocityX(-200);
        	}
        }
	}
	
	public void fireBullet() {
		if(this.isDead() || GamePause.isPause == true || GamePause.isReGame == true || EnemyGen.getbadList().size() == 0) {
			return;
		}
		Audio.ENEMY_FIRE.play();
		if(this.getFace() == "LEFT") {
			new Bullet(this.getPositionX()-100, this.getPositionY()+50, (Images.BULLET)[0]);
		} else {
			new Bullet(this.getPositionX()+100, this.getPositionY()+50, (Images.BULLET)[1]);
		}
	}
	
	public boolean isReadyToFire() {
		return this.isReadyToFire;
	}
	public void setFire(boolean isReady) {
		this.isReadyToFire = isReady;
	}
}
